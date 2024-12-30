package xyz.darkcomet.cogwheel.network.gateway.impl

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.events.Event
import xyz.darkcomet.cogwheel.events.GatewayHelloEvent
import xyz.darkcomet.cogwheel.events.GatewayReadyEvent
import xyz.darkcomet.cogwheel.impl.authentication.Token
import xyz.darkcomet.cogwheel.models.Intents
import xyz.darkcomet.cogwheel.models.ShardId
import xyz.darkcomet.cogwheel.network.CancellationToken
import xyz.darkcomet.cogwheel.network.CancellationTokenSource
import xyz.darkcomet.cogwheel.network.entities.GatewayIdentifyEventDataEntity
import xyz.darkcomet.cogwheel.network.gateway.CwGatewayClient
import xyz.darkcomet.cogwheel.network.gateway.GatewayEventMapping
import xyz.darkcomet.cogwheel.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.network.gateway.events.GatewayHeartbeatSendEvent
import xyz.darkcomet.cogwheel.network.gateway.events.GatewaySendEvent
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit

/*
    Discord Gateway client implementation backed by the Ktor library. 
    Specifications: https://discord.com/developers/docs/events/gateway
 */
internal class KtorGatewayClient 
private constructor(
    private val token: Token, 
    private val intents: Intents, 
    private val libName: String
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
    private var session: GatewaySession? = null 
    
    private val logger: Logger = LoggerFactory.getLogger(KtorGatewayClient::class.java)
    
    init {
        logger.info("Gateway HttpClient initialized")
    }

    override suspend fun startGatewayConnection(
        cancellationToken: CancellationToken, 
        gatewayUrlFetcher: suspend () -> String?
    ) {
        logger.info("Starting Gateway connection...")
        
        var sessionCount = 0
        var fetchUrl = lastFetchedGatewayUrl == null
        
        val serviceCancellation = object : CancellationTokenSource() {
            override fun isCanceled(): Boolean {
                return cancellationToken.isCanceled() || this.canceled.get() 
            }
        }

        while (!serviceCancellation.isCanceled()) {
            sessionCount++
            
            val sessionCancellation = object : CancellationTokenSource() {
                override fun isCanceled(): Boolean {
                    return serviceCancellation.isCanceled() || this.canceled.get()
                }
            }
            
            if (fetchUrl) {
                lastFetchedGatewayUrl = fetchGatewayUrl(gatewayUrlFetcher, sessionCancellation)
                fetchUrl = false
            }
            
            try {
                establishGatewaySession(lastFetchedGatewayUrl!!, sessionCount, sessionCancellation)
            } catch (exception: UnknownHostException) {
                if (sessionCount == 1) {
                    logger.warn("Invalid Gateway URL '{}' - refreshing cached address now...", lastFetchedGatewayUrl)
                    fetchUrl = true
                } else {
                    logger.error("Invalid Gateway URL '{}' after retry, aborting connection attempt", lastFetchedGatewayUrl)
                    serviceCancellation.cancel()
                }
            } catch (exception: ClosedReceiveChannelException) {
                logger.warn("Gateway receive channel closed, retrying connection soon...")
                sessionCancellation.cancel()
                delay(1000)
            }
        }
    }

    override fun onEventReceived(listener: (Event) -> Unit) {
        onEventReceived = listener
    }

    private suspend fun fetchGatewayUrl(
        gatewayUrlFetcher: suspend () -> String?, 
        sessionCancellation: CancellationTokenSource
    ): String? {
        logger.trace("Fetching Gateway URL...")
        var attempts = 0
        var gatewayUrl: String?

        do {
            attempts++
            gatewayUrl = gatewayUrlFetcher.invoke()
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

    private suspend fun establishGatewaySession(
        gatewayUrl: String, 
        sessionCount: Int, 
        sessionCancellation: CancellationToken
    ) {
        logger.trace("Establishing Gateway connection: GET {} (session {})", lastFetchedGatewayUrl, sessionCount)
        
        synchronized(this) {
            if (session != null) {
                session = null
            }
        }
        
        httpClient.wss(method = HttpMethod.Get, host = gatewayUrl) {
            logger.info("Connected to {} successfully!", gatewayUrl)
            val wssSession = this
            
            performHandshake(wssSession, sessionCancellation)
            
            val receiverJob = launch { eventReceiverLoop(this, wssSession, sessionCancellation) }
            val senderJob = launch { eventSenderLoop(this, wssSession, sessionCancellation) }
            
            while (!sessionCancellation.isCanceled()) {
                yield()
            }
            
            httpClient.close()
            
            receiverJob.join()
            senderJob.join()
            
            logger.info("Gateway connection closed permanently")
        }
    }

    private suspend fun performHandshake(
        wssSession: DefaultClientWebSocketSession, 
        cancellation: CancellationToken
    ) {
        val gatewaySession = GatewaySession(this, cancellation)
        
        synchronized(this) {
            this.session = gatewaySession
        }
        
        val helloEvent = receiveHelloEvent(wssSession, gatewaySession)
        sendIdentifyWithIntents(wssSession)
        val readyEvent = receiveReadyEvent(wssSession, gatewaySession)
        
        synchronized(this) {
            gatewaySession.initialize(
                apiVersion = readyEvent.data.v, 
                sessionId = readyEvent.data.sessionId,
                resumeGatewayUrl = readyEvent.data.resumeGatewayUrl,
                shard = ShardId.from(readyEvent.data.shard),
                heartbeatIntervalMs = helloEvent.data.heartbeatInterval
            )
        }

        gatewaySession.beginBackgroundHeartbeats()
    }

    private suspend fun receiveHelloEvent(
        wssSession: DefaultClientWebSocketSession, 
        gatewaySession: GatewaySession
    ): GatewayHelloEvent {
        val event = receiveEvent(wssSession, gatewaySession)

        if (event !is GatewayHelloEvent) {
            val description = if (event == null) "null" else event::class.simpleName
            throw IllegalStateException("Invalid first event during handshake: $description")
        }
        
        return event
    }

    private suspend fun sendIdentifyWithIntents(session: DefaultClientWebSocketSession) {
        val data = GatewayIdentifyEventDataEntity(
            token = token.value,
            properties = GatewayIdentifyEventDataEntity.IdentifyConnectionPropertiesEntity(
                os = System.getProperty("os.name"),
                browser = libName,
                device = libName
            ),
            intents = intents.value
        )
        
        val dataElement = Json.encodeToJsonElement(GatewayIdentifyEventDataEntity.serializer(), data)
        val payload = GatewayPayload(op = GatewayOpCode.IDENTIFY.code, d = dataElement)
        
        session.sendSerialized(payload)
    }

    private suspend fun receiveReadyEvent(
        wssSession: DefaultClientWebSocketSession, 
        gatewaySession: GatewaySession
    ): GatewayReadyEvent {
        val event = receiveEvent(wssSession, gatewaySession)
            
        if (event !is GatewayReadyEvent) {
            val description = if (event == null) "null" else event::class.simpleName
            throw IllegalStateException("Invalid second event during handshake: $description")
        }

        return event
    }

    private suspend fun eventReceiverLoop(
        scope: CoroutineScope,
        session: DefaultClientWebSocketSession,
        cancellationToken: CancellationToken
    ) {
        while (!cancellationToken.isCanceled()) {
            val payload = session.receiveDeserialized<GatewayPayload>()
            logger.trace("Received payload: {}", payload)
            
            val event = GatewayEventMapping.decode(payload)
            
            if (event != null) {
                scope.launch {
                    onEventReceived?.invoke(event)
                }
            }
        }
    }
    
    private suspend fun eventSenderLoop(
        coroutine: CoroutineScope, 
        session: DefaultClientWebSocketSession, 
        cancellation: CancellationToken
    ) {
        while (!cancellation.isCanceled()) {
            val sendEvent = eventSendQueue.poll()
            
            if (sendEvent == null) {
                yield()
                continue
            }
            
            coroutine.launch { 
                val payload = sendEvent.asPayload()
                session.sendSerialized(payload)
                
                logger.trace("Sent event: {}, payload: {}", sendEvent::class.simpleName, payload)
            }
        }
    }
    
    private suspend fun receiveEvent(
        wssSession: DefaultClientWebSocketSession, 
        gatewaySession: GatewaySession
    ): Event? {
        val payload = wssSession.receiveDeserialized<GatewayPayload>()
        val event = GatewayEventMapping.decode(payload)
        
        if (event != null) {
            logger.trace("Received event: {}, payload: {}", event.javaClass.simpleName, payload)
        } else {
            logger.warn("Unsupported event type from payload: {}", payload)
        }

        if (payload.s != null) {
            if (payload.s < (gatewaySession.lastReceivedSequenceNumber.get())) {
                logger.warn("Received Gateway payload has 's' < last locally received 's' number: {} < {}", payload.s, gatewaySession.lastReceivedSequenceNumber)
            }
            gatewaySession.lastReceivedSequenceNumber.set(payload.s)
        }
        
        if (event != null) {
            fireEventReceived(event)
        }
        
        return event
    }
    
    private fun fireEventReceived(event: Event) {
        onEventReceived?.invoke(event)
    }
    
    internal fun heartbeat(lastReceivedSequenceNumber: Int) {
        val event = GatewayHeartbeatSendEvent(lastReceivedSequenceNumber)
        sendEvent(event)
    }

    override fun sendEvent(event: GatewaySendEvent) {
        eventSendQueue.add(event)
    }

    class Factory : CwGatewayClient.Factory {
        override fun create(token: Token, intents: Intents, libName: String): CwGatewayClient {
            return KtorGatewayClient(token, intents, libName)
        }
    }
}