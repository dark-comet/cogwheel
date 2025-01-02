package xyz.darkcomet.cogwheel.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.Snowflake

@Serializable
data class ActivityLocationEntity(
    val id: String,
    val kind: String,
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake?
)