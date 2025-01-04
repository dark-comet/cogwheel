package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ForumTagObject(
    val id: Snowflake,
    val name: String,
    val moderated: Boolean,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null
)