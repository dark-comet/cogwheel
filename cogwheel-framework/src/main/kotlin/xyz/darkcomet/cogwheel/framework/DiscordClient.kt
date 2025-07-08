@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework

import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventListener
import xyz.darkcomet.cogwheel.core.events.EventType
import xyz.darkcomet.cogwheel.core.primitives.auth.*
import xyz.darkcomet.cogwheel.framework.modules.*

interface DiscordClient {
    
    fun restApi(): RestApiModules 
    
    suspend fun startGateway()
    fun stopGateway()
    
    fun <T : Event<*>> on(eventType: EventType<T>, listener: EventListener<T>)
    fun <T : Event<*>> off(eventType: EventType<T>, listener: EventListener<T>): Boolean
    
    interface RestApiModules {
        val application: ApplicationModule
        val applicationRoleConnectionMetadata: ApplicationRoleConnectionMetadataModule
        val auditLog: AuditLogModule
        val autoModeration: AutoModerationModule
        val channel: ChannelModule
        val emoji: EmojiModule
        val entitlement: EntitlementModule
        val gateway: GatewayModule
        val guild: GuildModule
        val guildScheduledEvent: GuildScheduledEventModule
        val guildTemplate: GuildTemplateModule
        val invite: InviteModule
        val message: MessageModule
        val poll: PollModule
        val sku: SkuModule
        val soundboard: SoundboardModule
        val stageInstance: StageInstanceModule
        val sticker: StickerModule
        val subscription: SubscriptionModule
        val user: UserModule
        val voice: VoiceModule
        val webhook: WebhookModule
    }
    
    companion object {
        
        @JvmStatic @JvmOverloads
        fun fromBotToken(token: String, config: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            return build(BotToken(token), config)
        }
        
        @JvmStatic @JvmOverloads
        fun fromOAuth2Token(token: String, config: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            return build(OAuth2Token(token), config)
        }
        
        private fun build(token: Token, config: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            val builder = DiscordClientBuilder.from(token)
            config?.invoke(builder)
            
            return builder.buildClient()
        }
    }
}