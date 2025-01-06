package xyz.darkcomet.cogwheel.core

import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.impl.authentication.BotToken
import xyz.darkcomet.cogwheel.core.impl.authentication.OAuth2Token
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.network.http.rest.*
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayPresenceUpdateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayRequestGuildMembersRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayVoiceStateUpdateRequestParameters

interface CwDiscordClient {
    
    fun restApi(): RestApi
    fun gatewayApi(): GatewayApi
    fun events(): EventManager
    
    suspend fun startGatewayConnection()
    
    fun stop()

    interface RestApi {
        val application: ApplicationResource
        val applicationRoleConnectionMetadata: ApplicationRoleConnectionMetadataResource
        val auditLog: AuditLogResource
        val autoModeration: AutoModerationResource
        val channel: ChannelResource
        val emoji: EmojiResource
        val entitlement: EntitlementResource
        val gateway: GatewayResource
        val guild: GuildResource
        val guildScheduledEvent: GuildScheduledEventResource
        val guildTemplate: GuildTemplateResource
        val invite: InviteResource
        val message: MessageResource
        val poll: PollResource
        val sku: SkuResource
        val soundboard: SoundboardResource
        val stageInstance: StageInstanceResource
        val sticker: StickerResource
        val subscription: SubscriptionResource
        val user: UserResource
        val voice: VoiceResource
        val webhook: WebhookResource
    }
    
    interface GatewayApi {
        fun requestGuildMembers(request: GatewayRequestGuildMembersRequestParameters)
        fun updateVoiceState(request: GatewayVoiceStateUpdateRequestParameters)
        fun updatePresence(request: GatewayPresenceUpdateRequestParameters)
    }
    
    interface EventManager {
        fun <T : Event<*>> subscribe(eventType: Class<T>, listener: (T) -> Unit)
        fun <T : Event<*>> unsubscribe(eventType: Class<T>, listener: (T) -> Unit)
    }
    
    companion object {
        fun fromBotToken(token: String, init: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            return build(BotToken(token), init)
        }

        fun fromOAuth2Token(token: String, init: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            return build(OAuth2Token(token), init)
        }

        private fun build(token: Token, init: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            val builder = CwDiscordClientBuilder(token)
            init?.invoke(builder)

            return builder.build()
        }
    }
}