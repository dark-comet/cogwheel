package xyz.darkcomet.cogwheel.core.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.CwDiscordClient
import xyz.darkcomet.cogwheel.core.network.http.rest.*
import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventSubscription
import xyz.darkcomet.cogwheel.core.events.EventType
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
import xyz.darkcomet.cogwheel.core.primitives.AssetLocation
import xyz.darkcomet.cogwheel.core.primitives.AssetSize
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.reflect.KType
import kotlin.reflect.typeOf

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
    private val assetLocator: CwDiscordClient.AssetLocator
    
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
        
        restApi = RestApiImpl()
        gatewayApi = GatewayApiImpl()
        eventManager = EventManagerImpl()
        assetLocator = AssetLocatorImpl(config.discordAssetBaseUrl)
        
        logger.info("DiscordClient initialized")
    }

    override suspend fun startGateway() {
        if (gatewayClient == null) {
            throw IllegalStateException("Gateway not initialized! Build DiscordClient with useGateway() first.")
        }
        
        gatewayClient.start(
            cancellationToken, 
            gatewayUrlFetcher = { restApi().gateway.getGatewayUrl().data?.url }
        )
    }

    override fun stopGateway() {
        cancellationToken.cancel()
    }

    override fun restApi(): CwDiscordClient.RestApi = restApi
    override fun gatewayApi(): CwDiscordClient.GatewayApi = gatewayApi
    override fun events(): CwDiscordClient.EventManager = eventManager
    override fun locateAsset(): CwDiscordClient.AssetLocator = assetLocator
    
    internal inner class RestApiImpl : CwDiscordClient.RestApi {
        private val restClient = this@CwDiscordClientImpl.restClient
        
        override val application = ApplicationResource(restClient)
        override val applicationRoleConnectionMetadata = ApplicationRoleConnectionMetadataResource(restClient)
        override val auditLog = AuditLogResource(restClient)
        override val autoModeration = AutoModerationResource(restClient)
        override val channel = ChannelResource(restClient)
        override val emoji = EmojiResource(restClient)
        override val entitlement = EntitlementResource(restClient)
        override val gateway = GatewayResource(restClient)
        override val guild = GuildResource(restClient)
        override val guildScheduledEvent = GuildScheduledEventResource(restClient)
        override val guildTemplate = GuildTemplateResource(restClient)
        override val invite = InviteResource(restClient)
        override val message = MessageResource(restClient)
        override val poll = PollResource(restClient)
        override val sku = SkuResource(restClient)
        override val soundboard = SoundboardResource(restClient)
        override val stageInstance = StageInstanceResource(restClient)
        override val sticker = StickerResource(restClient)
        override val subscription = SubscriptionResource(restClient)
        override val user = UserResource(restClient)
        override val voice = VoiceResource(restClient)
        override val webhook = WebhookResource(restClient)
    }
    
    internal inner class GatewayApiImpl : CwDiscordClient.GatewayApi {
        
        private val client = this@CwDiscordClientImpl.gatewayClient
        
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
    
    internal inner class EventManagerImpl : CwDiscordClient.EventManager {
        
        private val listeners: MutableMap<EventType<*>, ArrayList<(Event<*>) -> Unit>> = HashMap()
        private val delegateListeners: MutableMap<EventSubscription<*>, (Event<*>) -> Unit> = HashMap();
        
        init {
            this@CwDiscordClientImpl.gatewayClient?.onEventReceived { event ->
                listeners[event.type]?.forEach {
                    it.invoke(event)
                }
            }
        }
        
        override fun <T : Event<*>> subscribe(eventType: EventType<T>, listener: EventSubscription<T>) {
            val delegate: (Event<*>) -> Unit = {
                @Suppress("UNCHECKED_CAST")
                listener.eventReceived(it as T)
            }
            
            listeners.putIfAbsent(eventType, ArrayList())
            listeners[eventType]!!.add(delegate)
        }
        
        override fun <T : Event<*>> unsubscribe(eventType: EventType<T>, listener: EventSubscription<T>): Boolean {
            val delegate = delegateListeners.remove(listener) 
                ?: return false
            
            return listeners[eventType]?.remove(delegate) ?: false
        }
    }
    
    internal inner class AssetLocatorImpl(val baseUrl: String) : CwDiscordClient.AssetLocator {
        
        override fun customEmoji(emojiId: Snowflake, size: AssetSize): AssetLocation {
            return locate("emojis/${emojiId}.png", size)
        }

        override fun guildIcon(
            guildId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("icons/${guildId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildSplash(
            guildId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("splashes/${guildId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildDiscoverySplash(
            guildId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("discovery-splashes/${guildId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildBanner(
            guildId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("banners/${guildId}/${imageNameWithoutExtension}.png", size)
        }

        override fun userBanner(
            userId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("banners/${userId}/${imageNameWithoutExtension}.png", size)
        }

        override fun defaultUserAvatar(index: Long, size: AssetSize): AssetLocation {
            // API NOTE: 
            // ** In the case of the Default User Avatar endpoint, the value for index depends on 
            // whether the user is migrated to the new username system. For users on the new 
            // username system, index will be (user_id >> 22) % 6. For users on the legacy username 
            // system, index will be discriminator % 5.
            //
            // In the case of the Default User Avatar and Sticker endpoints, the size of images 
            // returned is constant with the "size" querystring parameter being ignored.
            return locate("embed/avatars/${index}.png", size)
        }

        override fun userAvatar(
            userId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("avatars/${userId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildMemberAvatar(
            guildId: Snowflake,
            userId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {
            
            return locate("guilds/${guildId}/users/${userId}/avatars/${imageNameWithoutExtension}.png", size)
        }

        override fun avatarDecoration(imageNameWithoutExtension: String, size: AssetSize): AssetLocation {
            return locate("avatar-decoration-presets/${imageNameWithoutExtension}.png", size)
        }

        override fun applicationIcon(
            applicationId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("app-icons/${applicationId}/${imageNameWithoutExtension}.png", size)
        }

        override fun applicationCover(
            applicationId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("app-icons/${applicationId}/${imageNameWithoutExtension}.png", size)
        }

        override fun applicationAsset(
            applicationId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("app-assets/${applicationId}/${imageNameWithoutExtension}.png", size)
        }

        override fun achievementIcon(
            applicationId: Snowflake,
            achievementId: Snowflake,
            iconHash: String,
            size: AssetSize
        ): AssetLocation {

            return locate("app-assets/${applicationId}/achievements/${achievementId}/icons/${iconHash}.png", size)
        }

        override fun storePageAsset(applicationId: Snowflake, assetId: String, size: AssetSize): AssetLocation {
            return locate("app-assets/${applicationId}/store/${assetId}.png", size)
        }

        override fun stickerPackBanner(assetId: Snowflake, size: AssetSize): AssetLocation {
            return locate("app-assets/710982414301790216/store/${assetId}.png", size)
        }

        override fun teamIcon(teamId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation {
            return locate("team-icons/${teamId}/${imageNameWithoutExtension}.png", size)
        }

        override fun sticker(stickerId: Snowflake, size: AssetSize): AssetLocation {
            return locate("stickers/${stickerId}.png", size)
        }

        override fun roleIcon(roleId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation {
            return locate("role-icons/${roleId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildScheduledEventCover(
            scheduledEventId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("guild-events/${scheduledEventId}/${imageNameWithoutExtension}.png", size)
        }

        override fun guildMemberBanner(
            guildId: Snowflake,
            userId: Snowflake,
            imageNameWithoutExtension: String,
            size: AssetSize
        ): AssetLocation {

            return locate("guilds/${guildId}/users/${userId}/banners/${imageNameWithoutExtension}.png", size)
        }

        private fun locate(route: String, size: AssetSize): AssetLocation {
            return AssetLocation(baseUrl + route)
        }

    }
}