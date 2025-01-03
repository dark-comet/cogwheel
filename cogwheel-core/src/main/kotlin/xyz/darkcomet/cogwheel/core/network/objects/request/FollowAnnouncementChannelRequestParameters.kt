package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class FollowAnnouncementChannelRequestParameters(
    @SerialName("webhook_channel_id") val webhookChannelId: Snowflake,
)