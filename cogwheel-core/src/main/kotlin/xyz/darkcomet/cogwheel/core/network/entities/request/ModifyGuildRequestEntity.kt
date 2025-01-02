package xyz.darkcomet.cogwheel.core.network.entities.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildRequestEntity(
    val name: String? = null,
    val region: String? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    val icon: String? = null,
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    val splash: String? = null,
    @SerialName("discovery_splash") val discoverySplash: String? = null,
    val banner: String? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
    @SerialName("rules_channel_id") val rulesChannelId: Snowflake? = null,
    @SerialName("public_updates_channel_id") val publicUpdatesChannelId: Snowflake? = null,
    @SerialName("preferred_locale") val preferredLocale: String? = null,
    val features: List<String>? = null,
    val description: String? = null,
    @SerialName("premium_progress_bar_enabled") val premiumProgressBarEnabled: Boolean? = null,
    @SerialName("safety_alerts_channel_id") val safetyAlertsChannelId: Snowflake? = null
)