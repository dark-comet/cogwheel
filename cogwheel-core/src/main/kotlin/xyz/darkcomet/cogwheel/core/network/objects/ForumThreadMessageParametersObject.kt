package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ForumThreadMessageParametersObject(
    val content: String?,
    val embeds: List<EmbedObject>,
    val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
    val stickerIds: List<Snowflake>? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null
)