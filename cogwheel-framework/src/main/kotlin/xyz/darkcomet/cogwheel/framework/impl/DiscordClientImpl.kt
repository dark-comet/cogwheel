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
        override val application = ApplicationApi(cwClient.restApi().application)
        override val applicationRoleConnectionMetadata = ApplicationRoleConnectionMetadataApi(cwClient.restApi().applicationRoleConnectionMetadata)
        override val auditLog = AuditLogApi(cwClient.restApi().auditLog)
        override val autoModeration = AutoModerationApi(cwClient.restApi().autoModeration)
        override val channel: ChannelApi = ChannelApi(cwClient.restApi().channel)
        override val emoji: EmojiApi = EmojiApi(cwClient.restApi().emoji)
        override val entitlement: EntitlementApi = EntitlementApi(cwClient.restApi().entitlement)
        override val gateway: GatewayApi = GatewayApi(cwClient.restApi().gateway)
        override val guild: GuildApi = GuildApi(cwClient.restApi().guild)
        override val guildScheduledEvent = GuildScheduledEventApi(cwClient.restApi().guildScheduledEvent)
        override val guildTemplate = GuildTemplateApi(cwClient.restApi().guildTemplate)
        override val invite = InviteApi(cwClient.restApi().invite)
        override val message = MessageApi(cwClient.restApi().message)
        override val poll = PollApi(cwClient.restApi().poll)
        override val sku = SkuApi(cwClient.restApi().sku)
        override val soundboard = SoundboardApi(cwClient.restApi().soundboard)
        override val stageInstance = StageInstanceApi(cwClient.restApi().stageInstance)
        override val sticker = StickerApi(cwClient.restApi().sticker)
        override val subscription = SubscriptionApi(cwClient.restApi().subscription)
        override val user = UserApi(cwClient.restApi().user)
        override val voice = VoiceApi(cwClient.restApi().voice)
        override val webhook = WebhookApi(cwClient.restApi().webhook)
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