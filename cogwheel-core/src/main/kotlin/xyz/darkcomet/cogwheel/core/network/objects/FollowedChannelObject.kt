package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class FollowedChannelObject(
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("webhook_id") val webhookId: Snowflake
)