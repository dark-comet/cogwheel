package xyz.darkcomet.cogwheel.framework.impl

import xyz.darkcomet.cogwheel.core.CwDiscordClient
import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventSubscription
import xyz.darkcomet.cogwheel.core.events.EventType
import xyz.darkcomet.cogwheel.framework.DiscordClient
import xyz.darkcomet.cogwheel.framework.modules.*

internal class DiscordClientImpl(private val cwClient: CwDiscordClient) : DiscordClient {
    
    private val modules = RestApiModulesImpl()
    
    override fun modules(): DiscordClient.RestApiModules {
        return modules
    }
    
    private inner class RestApiModulesImpl : DiscordClient.RestApiModules {
        override val application = ApplicationModule(cwClient.restApi().application)
        override val applicationRoleConnectionMetadata = ApplicationRoleConnectionMetadataModule(cwClient.restApi().applicationRoleConnectionMetadata)
        override val auditLog = AuditLogModule(cwClient.restApi().auditLog)
        override val autoModeration = AutoModerationModule(cwClient.restApi().autoModeration)
        override val channel: ChannelModule = ChannelModule(cwClient.restApi().channel)
        override val emoji: EmojiModule = EmojiModule(cwClient.restApi().emoji)
        override val entitlement: EntitlementModule = EntitlementModule(cwClient.restApi().entitlement)
        override val gateway: GatewayModule = GatewayModule(cwClient.restApi().gateway)
        override val guild: GuildModule = GuildModule(cwClient.restApi().guild)
        override val guildScheduledEvent = GuildScheduledEventModule(cwClient.restApi().guildScheduledEvent)
        override val guildTemplate = GuildTemplateModule(cwClient.restApi().guildTemplate)
        override val invite = InviteModule(cwClient.restApi().invite)
        override val message = MessageModule(cwClient.restApi().message)
        override val poll = PollModule(cwClient.restApi().poll)
        override val sku = SkuModule(cwClient.restApi().sku)
        override val soundboard = SoundboardModule(cwClient.restApi().soundboard)
        override val stageInstance = StageInstanceModule(cwClient.restApi().stageInstance)
        override val sticker = StickerModule(cwClient.restApi().sticker)
        override val subscription = SubscriptionModule(cwClient.restApi().subscription)
        override val user = UserModule(cwClient.restApi().user)
        override val voice = VoiceModule(cwClient.restApi().voice)
        override val webhook = WebhookModule(cwClient.restApi().webhook)
    }

    override suspend fun startGateway()
        = cwClient.startGateway()

    override fun stopGateway()
        = cwClient.stopGateway()

    override fun <T : Event<*>> on(eventType: EventType<T>, listener: EventSubscription<T>) 
        = cwClient.events().subscribe(eventType, listener)

    override fun <T : Event<*>> off(eventType: EventType<T>, listener: EventSubscription<T>): Boolean 
        = cwClient.events().unsubscribe(eventType, listener)

}