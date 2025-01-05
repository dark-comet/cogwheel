package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateMessageRequestParameters(
    val content: String? = null,
    val nonce: String? = null,
    val tts: Boolean? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    @SerialName("message_reference") val messageReference: MessageReferenceObject? = null,
    val components: List<MessageComponentObject>? = null,
    @SerialName("sticker_ids") val stickerIds: List<Snowflake>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null,
    @SerialName("enforce_nonce") val enforceNonce: Boolean? = null,
    val poll: PollObject? = null
)