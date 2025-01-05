package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class StageInstanceObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Int,
    @SerialName("discoverable_disabled") val discoverableDisabled: Boolean,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake? = null,
)