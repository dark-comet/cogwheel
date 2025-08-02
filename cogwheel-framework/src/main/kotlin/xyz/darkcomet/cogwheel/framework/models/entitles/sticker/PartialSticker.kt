@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.sticker

import xyz.darkcomet.cogwheel.core.network.objects.StickerObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.StickerFormatType
import xyz.darkcomet.cogwheel.framework.primitives.StickerId
import xyz.darkcomet.cogwheel.framework.primitives.asStickerId

open class PartialSticker(
    val id: StickerId,
    val name: String,
    val formatType: StickerFormatType
) {
    companion object {
        internal fun from(obj: StickerObject): PartialSticker {
            return obj.toPartialModel()
        }
    }
}

internal fun StickerObject.toPartialModel(): PartialSticker {
    return PartialSticker(
        id = requireNonNull(this, StickerObject::id).asStickerId(),
        name = requireNonNull(this, StickerObject::name),
        formatType = requireNonNull(this, StickerObject::formatType).let { StickerFormatType.fromOrAdd(it) }
    )
}