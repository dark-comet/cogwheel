@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.poll

import xyz.darkcomet.cogwheel.core.network.objects.PollAnswerObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class PollAnswer(
    val answerId: Int?,
    val pollMedia: PollMedia,
) {
    companion object {
        internal fun from(obj: PollAnswerObject): PollAnswer {
            return obj.toModel()
        }
    }
}

internal fun PollAnswerObject.toModel(): PollAnswer {
    return PollAnswer(
        answerId = requireNonNullIfPresent(this, PollAnswerObject::answerId),
        pollMedia = requireNonNull(this, PollAnswerObject::pollMedia).toModel()
    )
}