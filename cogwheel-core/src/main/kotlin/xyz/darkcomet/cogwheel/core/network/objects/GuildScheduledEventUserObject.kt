package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildScheduledEventUserObject(
    val guildScheduledEventId: Snowflake,
    val user: UserObject,
    val member: GuildMemberObject? = null
)