package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ThreadMemberObject(
    val id: Snowflake? = null,
    @SerialName("user_id") val userId: Snowflake? = null,
    @SerialName("join_timestamp") val joinTimestamp: ISO8601Timestamp,
    val flags: Int,
    val member: GuildMemberObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null
)