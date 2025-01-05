package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateStageInstanceRequestParameters(
    @SerialName("channel_id") val channelId: Snowflake,
    val topic: String,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("send_start_notification") val sendStartNotification: Boolean? = null,
    @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake? = null
)