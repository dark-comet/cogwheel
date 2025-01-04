package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AutoModerationActionMetadataObject(
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("duration_seconds") val durationSeconds: Int,
    @SerialName("custom_message") val customMessage: String
)