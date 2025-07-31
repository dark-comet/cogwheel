@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.poll

import xyz.darkcomet.cogwheel.core.network.objects.PollObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.PollLayoutType

class Poll(
    val question: PollMedia,
    val answers: List<PollAnswer>,
    val expiry: ISO8601Timestamp?,
    val allowMultiselect: Boolean,
    val layoutType: PollLayoutType,
    val results: PollResults?
) {
    companion object {
        internal fun from(obj: PollObject): Poll {
            return obj.toModel()
        }
    }
}

internal fun PollObject.toModel(): Poll {
    return Poll(
        question = requireNonNull(this, PollObject::question).toModel(),
        answers = requireNonNull(this, PollObject::answers).map { it.toModel() },
        expiry = require(this, PollObject::expiry),
        allowMultiselect = requireNonNull(this, PollObject::allowMultiselect),
        layoutType = requireNonNull(this, PollObject::layoutType).let { PollLayoutType.fromOrAdd(it) },
        results = requireNonNullIfPresent(this, PollObject::results)?.toModel()
    )
}