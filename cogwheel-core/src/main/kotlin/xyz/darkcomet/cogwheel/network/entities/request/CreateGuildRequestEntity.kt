package xyz.darkcomet.cogwheel.network.entities.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.*
import xyz.darkcomet.cogwheel.network.entities.ChannelEntity
import xyz.darkcomet.cogwheel.network.entities.RoleEntity

@Serializable
data class CreateGuildRequestEntity(
    val name: String,
    val region: String? = null, // deprecated
    val icon: String? = null,
    @SerialName("verification_level") val verificationLevel: Int? = null,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int? = null,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int? = null,
    val roles: List<RoleEntity>? = null,
    val channels: List<ChannelEntity>? = null,
    @SerialName("afk_channel_id") val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout") val afkTimeout: Int? = null,
    @SerialName("system_channel_id") val systemChannelId: Snowflake? = null,
    @SerialName("system_channel_flags") val systemChannelFlags: Int? = null,
)