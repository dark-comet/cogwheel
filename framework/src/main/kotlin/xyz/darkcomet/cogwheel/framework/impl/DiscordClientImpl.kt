package xyz.darkcomet.cogwheel.framework.impl

import xyz.darkcomet.cogwheel.core.CwDiscordClient
import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventListener
import xyz.darkcomet.cogwheel.core.events.EventType
import xyz.darkcomet.cogwheel.framework.DiscordClient
import xyz.darkcomet.cogwheel.framework.restapi.*

internal class DiscordClientImpl(private val cwClient: CwDiscordClient) : DiscordClient {
    
    private val restApi = RestApiImpl()
    
    override fun restApi(): DiscordClient.RestApi {
        return restApi
    }

    private inner class RestApiImpl : DiscordClient.RestApi {
        private val rest = cwClient.restApi()
        
        override val application = ApplicationApi(rest.application)
        override val applicationRoleConnectionMetadata = ApplicationRoleConnectionMetadataApi(rest.applicationRoleConnectionMetadata)
        override val auditLog = AuditLogApi(rest.auditLog)
        override val autoModeration = AutoModerationApi(rest.autoModeration)
        override val channel: ChannelApi = ChannelApi(rest.channel)
        override val emoji: EmojiApi = EmojiApi(rest.emoji)
        override val entitlement: EntitlementApi = EntitlementApi(rest.entitlement)
        override val gateway: GatewayApi = GatewayApi(rest.gateway)
        override val guild: GuildApi = GuildApi(rest.guild)
        override val guildScheduledEvent = GuildScheduledEventApi(rest.guildScheduledEvent)
        override val guildTemplate = GuildTemplateApi(rest.guildTemplate)
        override val invite = InviteApi(rest.invite)
        override val message = MessageApi(rest.message)
        override val poll = PollApi(rest.poll)
        override val sku = SkuApi(rest.sku)
        override val soundboard = SoundboardApi(rest.soundboard)
        override val stageInstance = StageInstanceApi(rest.stageInstance)
        override val sticker = StickerApi(rest.sticker)
        override val subscription = SubscriptionApi(rest.subscription)
        override val user = UserApi(rest.user)
        override val voice = VoiceApi(rest.voice)
        override val webhook = WebhookApi(rest.webhook)
    }
    
    override suspend fun startGateway()
        = cwClient.startGateway()

    override fun stopGateway()
        = cwClient.stopGateway()

    override fun <T : Event<*>> on(eventType: EventType<T>, listener: EventListener<T>) 
        = cwClient.events().subscribe(eventType, listener)

    override fun <T : Event<*>> off(eventType: EventType<T>, listener: EventListener<T>): Boolean 
        = cwClient.events().unsubscribe(eventType, listener)

}