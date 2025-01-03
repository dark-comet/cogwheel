package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ActivityEmojiObject(
    val name: String,
    val id: Snowflake? = null,
    val animated: Boolean? = null
)