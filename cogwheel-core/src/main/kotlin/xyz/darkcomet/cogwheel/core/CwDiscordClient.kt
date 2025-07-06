package xyz.darkcomet.cogwheel.core

import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.events.EventSubscription
import xyz.darkcomet.cogwheel.core.events.EventType
import xyz.darkcomet.cogwheel.core.primitives.auth.BotToken
import xyz.darkcomet.cogwheel.core.primitives.auth.OAuth2Token
import xyz.darkcomet.cogwheel.core.primitives.auth.Token
import xyz.darkcomet.cogwheel.core.network.http.rest.*
import xyz.darkcomet.cogwheel.core.network.objects.GatewayPresenceUpdateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GatewayRequestGuildMembersRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GatewayVoiceStateUpdateRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.AssetLocation
import xyz.darkcomet.cogwheel.core.primitives.AssetSize
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

interface CwDiscordClient {
    
    fun restApi(): RestApi
    fun gatewayApi(): GatewayApi
    fun events(): EventManager
    fun locateAsset(): AssetLocator
    
    suspend fun startGateway()
    fun stopGateway()

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
        fun <T : Event<*>> subscribe(eventType: EventType<T>, listener: EventSubscription<T>)
        fun <T : Event<*>> unsubscribe(eventType: EventType<T>, listener: EventSubscription<T>): Boolean
    }
    
    interface AssetLocator {
        fun customEmoji(emojiId: Snowflake, size: AssetSize): AssetLocation
        fun guildIcon(guildId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildSplash(guildId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildDiscoverySplash(guildId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildBanner(guildId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun userBanner(userId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun defaultUserAvatar(index: Long, size: AssetSize): AssetLocation
        fun userAvatar(userId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildMemberAvatar(guildId: Snowflake, userId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun avatarDecoration(imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun applicationIcon(applicationId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun applicationCover(applicationId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun applicationAsset(applicationId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun achievementIcon(applicationId: Snowflake, achievementId: Snowflake, iconHash: String, size: AssetSize): AssetLocation
        fun storePageAsset(applicationId: Snowflake, assetId: String, size: AssetSize): AssetLocation
        fun stickerPackBanner(assetId: Snowflake, size: AssetSize): AssetLocation
        fun teamIcon(teamId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun sticker(stickerId: Snowflake, size: AssetSize): AssetLocation
        fun roleIcon(roleId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildScheduledEventCover(scheduledEventId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
        fun guildMemberBanner(guildId: Snowflake, userId: Snowflake, imageNameWithoutExtension: String, size: AssetSize): AssetLocation
    }
    
    companion object {
        @JvmStatic
        fun fromBotToken(token: String, config: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            return build(BotToken(token), config)
        }

        @JvmStatic
        fun fromOAuth2Token(token: String, config: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            return build(OAuth2Token(token), config)
        }

        private fun build(token: Token, config: (CwDiscordClientBuilder.() -> Unit)? = null): CwDiscordClient {
            val builder = CwDiscordClientBuilder.from(token)
            config?.invoke(builder)

            return builder.build()
        }
    }
}