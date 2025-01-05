package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyUserVoiceStateRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val suppress: Boolean? = null
)