package xyz.darkcomet.cogwheel.network.http.requests.guild

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.*

@Serializable
data class CreateGuildRequest(
    val name: String,
    val region: String? = null, // deprecated
    val icon: ImageData? = null,
    @SerialName("verification_level") val verificationLevel: GuildVerificationLevel? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: GuildDefaultMessageNotificationLevel? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: GuildExplicitContentFilterLevel? = null,
    val roles: List<Role>? = null,
    val channels: List<Channel>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: GuildSystemChannelFlags? = null,
)