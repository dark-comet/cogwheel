package xyz.darkcomet.cogwheel.core.network.gateway.impl

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okio.IOException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects.Gateway.ConnectionAttemptStartedArgs
import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects.Gateway.FetchGatewayUrlCompleteArgs
import xyz.darkcomet.cogwheel.core.events.*
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.models.Intents
import xyz.darkcomet.cogwheel.core.models.ShardId
import xyz.darkcomet.cogwheel.core.network.CancellationToken
import xyz.darkcomet.cogwheel.core.network.CancellationTokenSource
import xyz.darkcomet.cogwheel.core.network.gateway.CwGatewayClient
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayEventDecoder
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayCloseCode
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayHeartbeatSendEvent
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayIdentifySendEvent
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayResumeSendEvent
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewaySendEvent
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.CancellationException
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

/*
    Discord Gateway client implementation backed by the Ktor library. 
    Specifications: https://discord.com/developers/docs/events/gateway
 */
internal class KtorGatewayClient 
private constructor(
    private val token: Token,
    private val intents: Intents,
    private val libName: String,
    private val aspects: DiscordClientAspects.Gateway,
    private val settings: CwGatewayClient.Settings
) : CwGatewayClient {
    
    private val httpClient: HttpClient = HttpClient(OkHttp) {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
        
        engine {
            preconfigured = OkHttpClient.Builder().pingInterval(20, TimeUnit.SECONDS).build()
        }
    }

    private var lastFetchedGatewayUrl: String? = null
    
    private val eventSendQueue: Queue<GatewaySendEvent> = ConcurrentLinkedQueue()
    private var onEventReceived: ((Event) -> Unit)? = null
    private var gatewaySession: KtorGatewaySession? = null
    
    private val started = AtomicBoolean(false)
    private val logger: Logger = LoggerFactory.getLogger(KtorGatewayClient::class.java)
    
    init {
        if (settings.fetchUrlMaxAttempts != null && settings.fetchUrlMaxAttempts <= 0) {
            throw IllegalArgumentException("fetchUrlMaxAttempts must be > 0 if set")
        }
        if (settings.reconnectMaxAttempts != null && settings.reconnectMaxAttempts <= 0) {
            throw IllegalArgumentException("reconnectMaxAttempts must be > 0 if set")
        }
        
        logger.info("Gateway HttpClient initialized")
    }

    override suspend fun start(
        cancellationToken: CancellationToken,
        gatewayUrlFetcher: suspend () -> String?
    ) {
        if (started.getAndSet(true)) {
            throw IllegalStateException("Already started")
        }
        
        var sessionCount = 0
        
        val serviceCancellation = object : CancellationTokenSource() {
            override fun isCanceled(): Boolean {
                return cancellationToken.isCanceled() || this.canceled.get() 
            }
        }

        // Atomic because it's mutated during gateway session based on close condition to 
        // determine whether to attempt resume.
        val shouldResumeNextAttempt = AtomicBoolean(false)
        
        while (!serviceCancellation.isCanceled()) {
            sessionCount++
            
            if (settings.reconnectMaxAttempts != null && sessionCount > settings.reconnectMaxAttempts) {
                logger.info("Aborted Gateway connection attempt: max attempt reached ({})", sessionCount)
                break;
            }
            
            val sessionCancellation = object : CancellationTokenSource() {
                override fun isCanceled(): Boolean {
                    return serviceCancellation.isCanceled() || this.canceled.get()
                }
            }
            
            var isResume: Boolean
            var gatewayUrl: String

            val hasExistingSession = gatewaySession?.resumeGatewayUrl != null && gatewaySession?.sessionId != null
            val shouldResumeNextAttemptOldValue = shouldResumeNextAttempt.get()
            
            if (!hasExistingSession || !shouldResumeNextAttempt.get()) {
                // Unable to resume, start a new connection
                isResume = false
                shouldResumeNextAttempt.set(true)
                
                if (lastFetchedGatewayUrl == null) {
                    lastFetchedGatewayUrl = fetchGatewayUrl(gatewayUrlFetcher, sessionCancellation)
                }
                gatewayUrl = lastFetchedGatewayUrl!!
            } else {
                // A previous gateway session was active, try resume
                isResume = true
                shouldResumeNextAttempt.set(true)
                
                assert(gatewaySession != null)
                assert(gatewaySession!!.sessionId != null)
                assert(gatewaySession!!.resumeGatewayUrl != null)

                gatewayUrl = gatewaySession!!.resumeGatewayUrl!!
            }

            logger.trace("Establishing Gateway connection: GET {} (session {})", gatewayUrl, sessionCount)
            
            val adviceArgs = ConnectionAttemptStartedArgs(sessionCount, isResume, shouldResumeNextAttemptOldValue)
            aspects.connectionAttemptStarted.applyAdvices(adviceArgs)
            
            try {
                establishWebsocketConnection(gatewayUrl, isResume, shouldResumeNextAttempt, sessionCancellation)
            } catch (exception: UnknownHostException) {
                // TODO: Handle the case where resumeUrl may be invalid -- need to force reconnection
                //       think about how to reliably tell this case apart from a loss of internet connectivity?
                logger.warn("Unreachable gateway URL: {}, retrying...", gatewayUrl)
                if (!isResume) {
                    lastFetchedGatewayUrl = fetchGatewayUrl(gatewayUrlFetcher, sessionCancellation)
                }
            } catch (exception: ClosedReceiveChannelException) {
                logger.warn("Gateway receive channel closed, retrying connection soon...")
                sessionCancellation.cancel()
            }

            delay(1000)
        }
    }

    private suspend fun establishWebsocketConnection(
        gatewayUrl: String,
        isResume: Boolean,
        shouldResumeNextAttempt: AtomicBoolean,
        sessionCancellation: CancellationTokenSource
    ) {
        httpClient.wss(method = HttpMethod.Get, host = gatewayUrl) {
            logger.info("Connected to {}", gatewayUrl)
            launchGateway(this, isResume, shouldResumeNextAttempt, sessionCancellation)
            handleCloseCode(this, shouldResumeNextAttempt)
        }
    }

    private suspend fun handleCloseCode(
        wssSession: DefaultClientWebSocketSession,
        shouldResumeNextAttempt: AtomicBoolean
    ) {
        try {
            // Verify it is indeed sensible to resume rather than re-identify based on 
            // websocket close code specified by https://discord.com/developers/docs/topics/opcodes-and-status-codes
            val rawCloseCode = wssSession.closeReason.await()?.code
            var gatewayCloseCode: GatewayCloseCode? = null

            if (shouldResumeNextAttempt.get()) {
                gatewayCloseCode = GatewayCloseCode.from(rawCloseCode)
                shouldResumeNextAttempt.set(gatewayCloseCode?.shouldResume ?: true)
            }

            logger.trace(
                "Gateway WSS close code: {}, GatewayCloseCode: {}, resumeNext: {}",
                rawCloseCode,
                gatewayCloseCode,
                shouldResumeNextAttempt.get()
            )
        } catch (exception: IOException) {
            // Can happen when there is no internet. Ignore & retry.
        }
    }

    override fun onEventReceived(listener: (Event) -> Unit) {
        onEventReceived = listener
    }

    private suspend fun fetchGatewayUrl(
        gatewayUrlFetcher: suspend () -> String?, 
        sessionCancellation: CancellationToken
    ): String? {
        logger.trace("Fetching Gateway URL...")
        var attempts = 0
        var gatewayUrl: String? = null

        do {
            attempts++

            if (settings.fetchUrlMaxAttempts != null && attempts > settings.fetchUrlMaxAttempts) {
                logger.info("Aborted Gateway URL fetch: exceeded max attempt limit ({})", attempts)
                break
            }
            
            try {
                gatewayUrl = gatewayUrlFetcher.invoke()
                
                val adviceArgs = FetchGatewayUrlCompleteArgs(gatewayUrl)
                aspects.fetchGatewayUrlComplete.applyAdvices(adviceArgs)
            } catch (exception: UnknownHostException) {
                logger.warn("Fetch Gateway URL attempt {}, bad response: UnknownHostException", attempts)
                gatewayUrl = null
                delay(1000)
                continue
            }
            
            logger.trace("Fetch Gateway URL attempt {}, got response: {}", attempts, gatewayUrl)
            
            if (gatewayUrl != null) {
                gatewayUrl = gatewayUrl.replace("wss://", "").trim()
            }
            
            if (sessionCancellation.isCanceled()) {
                logger.trace("Aborted Gateway URL fetch: session canceled")
                break
            }
        } while (gatewayUrl == null)
        
        return gatewayUrl
    }

    private suspend fun launchGateway(
        wssSession: DefaultClientWebSocketSession,
        isResume: Boolean,
        shouldResumeNextAttempt: AtomicBoolean,
        sessionCancellation: CancellationToken
    ) {
        if (!shouldResumeNextAttempt.get() && gatewaySession != null) {
            gatewaySession = null
        }

        val success = performHandshake(wssSession, isResume, shouldResumeNextAttempt)
        
        if (!success) {
            wssSession.close()
            return
        }

        val receiverJob = wssSession.launch { eventReceiverLoop(this, wssSession, shouldResumeNextAttempt) }
        val senderJob = wssSession.launch { eventSenderLoop(this, wssSession) }
        
        while (!sessionCancellation.isCanceled() && wssSession.isActive) {
            yield()
        }
        
        wssSession.close()
            
        receiverJob.join()
        senderJob.join()

        logger.info("Gateway connection closed: {}, {}", sessionCancellation.isCanceled(), wssSession.isActive)
    }

    private suspend fun performHandshake(
        wssSession: DefaultClientWebSocketSession,
        isResume: Boolean,
        shouldResumeNextAttempt: AtomicBoolean
    ): Boolean {
        if (isResume) {
            assert(gatewaySession != null)
        } else {
            this.gatewaySession = KtorGatewaySession(this, wssSession)
        }

        val gatewaySession = this.gatewaySession!!
        
        val helloEvent = receiveHelloEvent(wssSession, gatewaySession, shouldResumeNextAttempt)
        
        if (isResume) {
            resumeGatewaySession(wssSession, gatewaySession)
        } else {
            sendIdentifyWithIntents(wssSession)
            val readyEvent = receiveReadyEvent(wssSession, gatewaySession, shouldResumeNextAttempt)
            
            if (readyEvent == null) {
                this.gatewaySession = null
                return false
            }

            gatewaySession.initialize(
                apiVersion = readyEvent.data.v,
                sessionId = readyEvent.data.sessionId,
                resumeGatewayUrl = readyEvent.data.resumeGatewayUrl.replace("wss://", "").trim(),
                shard = ShardId.from(readyEvent.data.shard),
                heartbeatIntervalMs = helloEvent.data.heartbeatInterval
            )
            
            if (!settings.disableHeartbeats) {
                gatewaySession.beginBackgroundHeartbeats()    
            }
        }
        
        return true
    }

    private suspend fun receiveHelloEvent(
        wssSession: DefaultClientWebSocketSession,
        gatewaySession: KtorGatewaySession,
        isResume: AtomicBoolean
    ): GatewayHelloEvent {
        val event = receiveEvent(wssSession, wssSession, gatewaySession, isResume)

        if (event !is GatewayHelloEvent) {
            val description = if (event == null) "null" else event::class.simpleName
            throw IllegalStateException("Invalid first event during handshake: $description")
        }
        
        return event
    }

    private suspend fun sendIdentifyWithIntents(session: DefaultClientWebSocketSession) {
        val event = GatewayIdentifySendEvent(token, intents, libName)
        sendEvent(event, session)
    }

    private suspend fun receiveReadyEvent(
        wssSession: DefaultClientWebSocketSession,
        gatewaySession: KtorGatewaySession,
        isResume: AtomicBoolean
    ): GatewayReadyEvent? {
        val event = receiveEvent(wssSession, wssSession, gatewaySession, isResume)
            
        if (event !is GatewayReadyEvent) {
            if (event is GatewayInvalidSessionEvent) {
                return null // Most likely caused by malformed argument in HELLO event, give up
            }
            
            val description = if (event == null) "null" else event::class.simpleName
            throw IllegalStateException("Invalid second event during handshake: $description")
        }

        return event
    }

    private suspend fun eventReceiverLoop(
        coroutineScope: CoroutineScope,
        wssSession: DefaultClientWebSocketSession,
        shouldResumeNextAttempt: AtomicBoolean
    ) {
        while (wssSession.isActive) {
            receiveEvent(coroutineScope, wssSession, this.gatewaySession!!, shouldResumeNextAttempt)
        }
    }
    
    private suspend fun eventSenderLoop(
        coroutine: CoroutineScope, 
        session: DefaultClientWebSocketSession
    ) {
        while (session.isActive) {
            val event = eventSendQueue.poll()
            
            if (event == null) {
                yield()
                continue
            }
            
            coroutine.launch { 
                sendEvent(event, session)
            }
        }
    }
    
    private suspend fun receiveEvent(
        coroutineScope: CoroutineScope,
        wssSession: DefaultClientWebSocketSession,
        gatewaySession: KtorGatewaySession,
        shouldResumeNextAttempt: AtomicBoolean
    ): Event? {
        val payload = wssSession.receiveDeserialized<GatewayPayload>()
        var event: Event? = null
        
        try {
            event = GatewayEventDecoder.decode(payload)
        } catch (exception: Exception) {
            logger.error("An error occurred while decoding payload: {}", payload, exception)
        } finally {
            if (event != null) {
                logger.trace("Received event: {}, payload: {}", event.javaClass.simpleName, payload)
            } else {
                logger.warn("Unsupported event type from payload: {}", payload)
            }
        }

        if (payload.s != null) {
            if (payload.s < (gatewaySession.lastReceivedSequenceNumber.get())) {
                logger.warn(
                    "Received Gateway payload has 's' < last locally received 's' number: {} < {}", 
                    payload.s, 
                    gatewaySession.lastReceivedSequenceNumber
                )
            } else {
                gatewaySession.lastReceivedSequenceNumber.set(payload.s)
            }
        }
        
        if (event != null) {
            coroutineScope.launch {
                fireEventReceived(event)
            }
            
            performSpecialEventHandling(event, wssSession, shouldResumeNextAttempt)
        }
        
        return event
    }

    private suspend fun performSpecialEventHandling(
        event: Event,
        wssSession: DefaultClientWebSocketSession,
        shouldResumeNextAttempt: AtomicBoolean
    ) {
        if (event is GatewayInvalidSessionEvent || event is GatewayReconnectEvent) {
            shouldResumeNextAttempt.set(true)

            if (event is GatewayInvalidSessionEvent) {
                shouldResumeNextAttempt.set(event.shouldTryResume)
            }

            wssSession.close()
        }
    }

    private suspend fun resumeGatewaySession(
        wssSession: DefaultClientWebSocketSession,
        gatewaySession: KtorGatewaySession
    ) {
        val sessionId = gatewaySession.sessionId
        
        if (sessionId == null) {
            wssSession.close()
            return
        }
        
        val seq = gatewaySession.lastReceivedSequenceNumber.get()
        val event = GatewayResumeSendEvent(token, sessionId, seq)
        sendEvent(event, wssSession)
    }

    private fun fireEventReceived(event: Event) {
        onEventReceived?.invoke(event)
    }
    
    internal fun heartbeat(lastReceivedSequenceNumber: Int) {
        val event = GatewayHeartbeatSendEvent(lastReceivedSequenceNumber)
        sendEventAsync(event)
    }

    override fun sendEventAsync(event: GatewaySendEvent) {
        eventSendQueue.add(event)
    }
    
    suspend fun sendEvent(event: GatewaySendEvent, session: DefaultClientWebSocketSession) {
        val payload = event.asPayload()
        session.sendSerialized(payload)

        logger.trace("Sent event: {}, payload: {}", event::class.simpleName, payload)
    }

    class Factory : CwGatewayClient.Factory {
        override fun create(
            token: Token,
            intents: Intents,
            libName: String,
            aspects: DiscordClientAspects.Gateway,
            settings: CwGatewayClient.Settings
        ): CwGatewayClient {
            return KtorGatewayClient(token, intents, libName, aspects, settings)
        }
    }
}