package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class MessageCallObject(
    val participants: List<Snowflake>,
    val endedTimestamp: ISO8601Timestamp? = null
)