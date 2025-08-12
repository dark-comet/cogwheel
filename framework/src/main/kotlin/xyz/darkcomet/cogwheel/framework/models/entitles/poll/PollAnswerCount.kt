@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.poll

import xyz.darkcomet.cogwheel.core.network.objects.PollAnswerCountObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull

class PollAnswerCount(
    val id: Int,
    val count: Int,
    val meVoted: Boolean
) {
    companion object {
        internal fun from(obj: PollAnswerCountObject): PollAnswerCount {
            return obj.toModel()
        }
    }
}

internal fun PollAnswerCountObject.toModel(): PollAnswerCount {
    return PollAnswerCount(
        id = requireNonNull(this, PollAnswerCountObject::id),
        count = requireNonNull(this, PollAnswerCountObject::count),
        meVoted = requireNonNull(this, PollAnswerCountObject::meVoted)
    )
}