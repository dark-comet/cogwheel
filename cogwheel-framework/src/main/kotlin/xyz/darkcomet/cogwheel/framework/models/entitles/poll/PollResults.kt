@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.poll

import xyz.darkcomet.cogwheel.core.network.objects.PollResultsObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull

class PollResults(
    val isFinalized: Boolean,
    val answerCounts: List<PollAnswerCount>
) {
    companion object {
        internal fun from(obj: PollResultsObject): PollResults {
            return obj.toModel()
        }
    }
}

internal fun PollResultsObject.toModel(): PollResults {
    return PollResults(
        isFinalized = requireNonNull(this, PollResultsObject::isFinalized),
        answerCounts = requireNonNull(this, PollResultsObject::answerCounts).map { it.toModel() }
    )
}