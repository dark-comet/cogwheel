package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ExecuteWebhookRequestParameters(
    val content: String? = null,
    val username: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    val tts: Boolean? = null,
    val embeds: List<EmbedObject>? = null,
    @SerialName("allowed_mentions") val allowedMentions: AllowedMentionsObject? = null,
    val components: List<MessageComponentObject>? = null,
//    val filesN
    @SerialName("payload_json") val payloadJson: String? = null,
    val attachments: List<AttachmentObject>? = null,
    val flags: Int? = null,
    @SerialName("thread_name") val threadName: String? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null,
    val poll: PollObject? = null
)