package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollResultsObject(
    @SerialName("is_finalized") val isFinalized: Boolean,
    @SerialName("answer_counts") val answerCounts: List<PollAnswerCountObject>
)