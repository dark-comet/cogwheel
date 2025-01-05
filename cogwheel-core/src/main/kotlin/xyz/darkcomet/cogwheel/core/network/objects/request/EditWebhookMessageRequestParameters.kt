package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.*

@Serializable
data class EditWebhookMessageRequestParameters(
    val content: String? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val poll: PollObject? = null
)