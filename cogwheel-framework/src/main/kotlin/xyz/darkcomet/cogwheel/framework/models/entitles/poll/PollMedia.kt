@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.poll

import xyz.darkcomet.cogwheel.core.network.objects.PollMediaObject
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.Emoji
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.toModel
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent

class PollMedia(
    val text: String?,
    val emoji: Emoji?
) {
    companion object {
        internal fun from(obj: PollMediaObject): PollMedia {
            return obj.toModel()
        }
    }
}

internal fun PollMediaObject.toModel(): PollMedia {
    return PollMedia(
        text = requireNonNullIfPresent(this, PollMediaObject::text),
        emoji = requireNonNullIfPresent(this, PollMediaObject::emoji)?.toModel()
    )
}