package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildWidgetSettingsObject(
    val enabled: Boolean,
    @SerialName("channel_id") val channelId: Snowflake? = null
)