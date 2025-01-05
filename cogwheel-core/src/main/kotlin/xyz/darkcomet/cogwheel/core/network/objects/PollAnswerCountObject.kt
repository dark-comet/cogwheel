package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollAnswerCountObject(
    val id: Int,
    val count: Int,
    @SerialName("me_voted") val meVoted: Boolean
)