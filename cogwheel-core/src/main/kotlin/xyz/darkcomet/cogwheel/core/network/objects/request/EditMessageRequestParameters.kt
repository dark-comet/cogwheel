package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.AllowedMentionsObject
import xyz.darkcomet.cogwheel.core.network.objects.AttachmentObject
import xyz.darkcomet.cogwheel.core.network.objects.EmbedObject
import xyz.darkcomet.cogwheel.core.network.objects.MessageComponentObject

@Serializable
data class EditMessageRequestParameters(
    val content: String? = null,
    val embeds: List<EmbedObject>? = null,
    val flags: Int? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
)