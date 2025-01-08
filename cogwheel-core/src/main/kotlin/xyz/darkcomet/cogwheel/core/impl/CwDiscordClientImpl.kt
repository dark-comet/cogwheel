package xyz.darkcomet.cogwheel.core.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.CwDiscordClient
import xyz.darkcomet.cogwheel.core.network.http.rest.*
import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.impl.models.CwConfiguration
import xyz.darkcomet.cogwheel.core.network.CancellationTokenSource
import xyz.darkcomet.cogwheel.core.network.gateway.CwGatewayClient
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayRequestGuildMembersSendEvent
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayUpdatePresenceSendEvent
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewayUpdateVoiceStateSendEvent
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.objects.GatewayPresenceUpdateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GatewayRequestGuildMembersRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GatewayVoiceStateUpdateRequestParameters
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

internal open class CwDiscordClientImpl 
internal constructor(settings: CwDiscordClientSettings) : CwDiscordClient {
    
    private val config: CwConfiguration = CwConfiguration.load()
    private val cancellationToken = CancellationTokenSource()
    private val logger: Logger = LoggerFactory.getLogger(CwDiscordClientImpl::class.java)

    private val restClient: CwHttpClient
    private val gatewayClient: CwGatewayClient?
    
    private val restApi: CwDiscordClient.RestApi
    private val gatewayApi: CwDiscordClient.GatewayApi
    private val eventManager: CwDiscordClient.EventManager
    
    init {
        logger.info("{} v{} initializing...", config.clientName, config.clientVersion)
        
        restClient = settings.cwHttpClientFactory.create(settings, config)
        logger.info("Initialized CwHttpClient: {}", restClient.javaClass.name)

        gatewayClient = if (settings.gatewayEnabled) {
            val gatewayClientSettings = CwGatewayClient.Settings(
                settings.jsonSerializer,
                settings.testDisableGatewayHeartbeats,
                settings.gatewayFetchUrlMaxAttempts,
                settings.gatewaySessionReconnectMaxAttempts
            )
            
            settings.cwGatewayClientFactory.create(
                settings.token, 
                settings.gatewayIntents, 
                config.clientName,
                settings.aspects.gateway,
                gatewayClientSettings
            )
        } else null
        
        restApi = RestApiImpl(restClient)
        gatewayApi = GatewayApiImpl(gatewayClient)
        eventManager = EventManagerImpl(gatewayClient)
        logger.info("DiscordClient initialized")
    }

    override suspend fun startGatewayConnection() {
        if (gatewayClient == null) {
            throw IllegalStateException("Gateway not initialized! Build DiscordClient with useGateway() first.")
        }
        
        gatewayClient.start(
            cancellationToken, 
            gatewayUrlFetcher = { restApi().gateway.getGatewayUrl().data?.url }
        )
    }

    override fun stop() {
        cancellationToken.cancel()
    }

    override fun restApi(): CwDiscordClient.RestApi = restApi
    override fun gatewayApi(): CwDiscordClient.GatewayApi = gatewayApi
    override fun events(): CwDiscordClient.EventManager = eventManager
    
    internal class RestApiImpl(client: CwHttpClient) : CwDiscordClient.RestApi {
        override val application = ApplicationResource(client)
        override val applicationRoleConnectionMetadata = ApplicationRoleConnectionMetadataResource(client)
        override val auditLog = AuditLogResource(client)
        override val autoModeration = AutoModerationResource(client)
        override val channel = ChannelResource(client)
        override val emoji = EmojiResource(client)
        override val entitlement = EntitlementResource(client)
        override val gateway = GatewayResource(client)
        override val guild = GuildResource(client)
        override val guildScheduledEvent = GuildScheduledEventResource(client)
        override val guildTemplate = GuildTemplateResource(client)
        override val invite = InviteResource(client)
        override val message = MessageResource(client)
        override val poll = PollResource(client)
        override val sku = SkuResource(client)
        override val soundboard = SoundboardResource(client)
        override val stageInstance = StageInstanceResource(client)
        override val sticker = StickerResource(client)
        override val subscription = SubscriptionResource(client)
        override val user = UserResource(client)
        override val voice = VoiceResource(client)
        override val webhook = WebhookResource(client)
    }
    
    internal class GatewayApiImpl(private val client: CwGatewayClient?) : CwDiscordClient.GatewayApi {
        
        override fun requestGuildMembers(request: GatewayRequestGuildMembersRequestParameters) {
            assertClientInitialized()
            
            val event = GatewayRequestGuildMembersSendEvent(request)
            client!!.sendEventAsync(event)
        }

        override fun updateVoiceState(request: GatewayVoiceStateUpdateRequestParameters) {
            assertClientInitialized()

            val event = GatewayUpdateVoiceStateSendEvent(request)
            client!!.sendEventAsync(event)
        }

        override fun updatePresence(request: GatewayPresenceUpdateRequestParameters) {
            assertClientInitialized()

            val event = GatewayUpdatePresenceSendEvent(request)
            client!!.sendEventAsync(event)
        }

        private fun assertClientInitialized() {
            if (client == null) {
                throw IllegalStateException("Gateway not initialized! Build DiscordClient with useGateway() first.")
            }
        }
        
    }
    
    internal class EventManagerImpl(client: CwGatewayClient?) : CwDiscordClient.EventManager {
        
        private val listeners: MutableMap<Class<out Any>, ArrayList<(Event<*>) -> Unit>> = HashMap()
        
        // TODO: Hacky -- is there a cleaner solution?
        //       Want listener to be of type "(T) -> Unit" rather than "(Event) -> Unit" for cleaner client code
        //       But JVM type-erasure makes this information hard to store.
        private val mapping: MutableMap<Any, (Event<*>) -> Unit> = HashMap()
        
        init {
            client?.onEventReceived { event ->
                listeners[event.javaClass]?.forEach {
                    it.invoke(event)
                }
            }
        }
        
        override fun <T : Event<*>> subscribe(eventType: Class<T>, listener: (T) -> Unit) {
            val delegate: (Event<*>) -> Unit = {
                @Suppress("UNCHECKED_CAST")
                listener.invoke(it as T)
            }
            
            mapping[listener] = delegate
            listeners.putIfAbsent(eventType, ArrayList())
            listeners[eventType]!!.add(delegate)
        }

        override fun <T : Event<*>> unsubscribe(eventType: Class<T>, listener: (T) -> Unit) {
            val delegateListener = mapping[listener]
            
            if (delegateListener != null) {
                listeners[eventType]?.remove(delegateListener)
            }
        }
    }
}