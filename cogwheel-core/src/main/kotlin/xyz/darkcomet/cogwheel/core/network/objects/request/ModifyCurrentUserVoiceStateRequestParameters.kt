package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyCurrentUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val suppress: Boolean? = null,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: ISO8601Timestamp? = null
)