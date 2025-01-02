package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class EmojiEntity(
    val id: Snowflake
)