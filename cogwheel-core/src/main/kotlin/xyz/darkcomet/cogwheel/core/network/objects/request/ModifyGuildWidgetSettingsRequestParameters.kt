package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildWidgetSettingsRequestParameters(
    val enabled: Boolean? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null
)