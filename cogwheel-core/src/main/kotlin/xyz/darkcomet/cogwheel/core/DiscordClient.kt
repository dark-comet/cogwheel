package xyz.darkcomet.cogwheel.core

import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.impl.authentication.BotToken
import xyz.darkcomet.cogwheel.core.impl.authentication.OAuth2Token
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.network.http.api.*

interface DiscordClient {
    
    fun restApi(): ClientRestApi
    fun gatewayApi(): ClientGatewayApi
    fun events(): ClientEventManager
    
    suspend fun startGatewayConnection()
    fun stop()

    interface ClientRestApi {
        fun application(): ApplicationApi
        fun applicationRoleConnectionMetadata(): ApplicationRoleConnectionMetadataApi
        fun auditLog(): AuditLogApi
        fun autoModeration(): AutoModerationApi
        fun channel(): ChannelApi
        fun emoji(): EmojiApi
        fun entitlement(): EntitlementApi
        fun gateway(): GatewayApi
        fun guild(): GuildApi
        fun guildScheduledEvent(): GuildScheduledEventApi
        fun guildTemplate(): GuildTemplateApi
        fun invite(): InviteApi
        fun message(): MessageApi
        fun poll(): PollApi
        fun sku(): SkuApi
        fun stageInstance(): StageInstanceApi
        fun sticker(): StickerApi
        fun subscription(): SubscriptionApi
        fun user(): UserApi
        fun voice(): VoiceApi
        fun webhook(): WebhookApi
    }
    
    interface ClientGatewayApi {
        fun requestGuildMembers() // TODO
        fun updateVoiceState() // TODO
        fun updatePresence() // TODO
    }
    
    interface ClientEventManager {
        fun <T : Event> subscribe(eventType: Class<T>, listener: (T) -> Unit)
        fun <T : Event> unsubscribe(eventType: Class<T>, listener: (T) -> Unit)
    }
    
    companion object {
        fun fromBotToken(token: String, init: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            return build(BotToken(token), init)
        }

        fun fromOAuth2Token(token: String, init: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            return build(OAuth2Token(token), init)
        }

        private fun build(token: Token, init: (DiscordClientBuilder.() -> Unit)? = null): DiscordClient {
            val builder = DiscordClientBuilder(token)
            init?.invoke(builder)

            return builder.build()
        }
    }
}