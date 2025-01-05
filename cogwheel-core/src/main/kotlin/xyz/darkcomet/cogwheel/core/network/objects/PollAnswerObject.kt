package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollAnswerObject(
    @SerialName("answer_id") val answerId: Int,
    @SerialName("poll_media") val pollMedia: PollMediaObject,
)