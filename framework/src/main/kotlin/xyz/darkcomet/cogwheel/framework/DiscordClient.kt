@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework

import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventListener
import xyz.darkcomet.cogwheel.core.events.EventType
import xyz.darkcomet.cogwheel.core.primitives.auth.*
import xyz.darkcomet.cogwheel.framework.restapi.*
import java.util.function.Consumer

interface DiscordClient {
    
    fun restApi(): RestApi 
    
    suspend fun startGateway()
    fun stopGateway()
    
    fun <T : Event<*>> on(eventType: EventType<T>, listener: EventListener<T>)
    fun <T : Event<*>> off(eventType: EventType<T>, listener: EventListener<T>): Boolean
    
    interface RestApi {
        val application: ApplicationApi
        val applicationRoleConnectionMetadata: ApplicationRoleConnectionMetadataApi
        val auditLog: AuditLogApi
        val autoModeration: AutoModerationApi
        val channel: ChannelApi
        val emoji: EmojiApi
        val entitlement: EntitlementApi
        val gateway: GatewayApi
        val guild: GuildApi
        val guildScheduledEvent: GuildScheduledEventApi
        val guildTemplate: GuildTemplateApi
        val invite: InviteApi
        val message: MessageApi
        val poll: PollApi
        val sku: SkuApi
        val soundboard: SoundboardApi
        val stageInstance: StageInstanceApi
        val sticker: StickerApi
        val subscription: SubscriptionApi
        val user: UserApi
        val voice: VoiceApi
        val webhook: WebhookApi
    }
    
    companion object {
        
        @JvmStatic @JvmOverloads
        fun fromBotToken(token: String, config: Consumer<DiscordClientBuilder>? = null): DiscordClient {
            return build(BotToken(token), config)
        }
        
        @JvmStatic @JvmOverloads
        fun fromOAuth2Token(token: String, config: Consumer<DiscordClientBuilder>? = null): DiscordClient {
            return build(OAuth2Token(token), config)
        }
        
        private fun build(token: Token, config: Consumer<DiscordClientBuilder>? = null): DiscordClient {
            val builder = DiscordClientBuilder.from(token)
            config?.accept(builder)
            
            return builder.buildClient()
        }
    }
}