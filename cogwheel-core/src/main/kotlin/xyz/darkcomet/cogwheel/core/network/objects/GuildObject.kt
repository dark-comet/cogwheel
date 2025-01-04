package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

/**
 * Low-level representation of Discord API Guild object and UnavailableGuild object.
 * 
 * @see <a href="https://discord.com/developers/docs/resources/guild">Discord API Reference: Guild</a>
 */
@Serializable
data class GuildObject(
    val id: Snowflake,
    val name: String? = null,
    val icon: String? = null,
    @SerialName("icon_hash") val iconHash: String? = null,
    val splash: String? = null,
    @SerialName("discovery_splash") val discoverySplash: String? = null,
    val owner: String? = null, //Only set when using the GET Current User Guilds endpoint, and are relative to the requested user
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    val permissions: Long? = null,
    @Deprecated("replaced by channel.rtc_region") val region: String? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    @SerialName("widget_enabled") val widgetEnabled: Boolean? = null,
    @SerialName("widget_channel_id") val widgetChannelId: Snowflake? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    val roles: List<GuildRoleObject>? = null,
    val emojis: List<EmojiObject>? = null,
    val features: List<String>? = null,
    @SerialName("mfa_level") val mfaLevel: Int? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Snowflake? = null,
    @SerialName("max_presences") val maxPresences: Int? = null,
    @SerialName("max_members") val maxMembers: Int? = null,
    @SerialName("vanity_url_code") val vanityUrlCode: String? = null,
    val description: String? = null,
    val banner: String? = null,
    @SerialName("premium_tier") val premiumTier: Int? = null,
    @SerialName("premium_subscription_count") val premiumSubscriptionCount: Int? = null,
    @SerialName("preferred_locale") val preferredLocale: String? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Snowflake? = null,
    @SerialName("max_video_channel_users") val maxVideoChannelUsers: Int? = null,
    @SerialName("max_stage_video_channel_users") val maxStageVideoChannelUsers: Int? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Int? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int? = null,
    @SerialName("welcome_screen") val welcomeScreen: GuildWelcomeScreenObject? = null,
    @SerialName("nsfw_level") val nsfwLevel: Int? = null,
    val stickers: List<StickerObject>? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Boolean? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Snowflake? = null,
    val unavailable: Boolean? = null
)
