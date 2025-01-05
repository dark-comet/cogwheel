package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ChannelMentionObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val type: Int,
    val name: String
)