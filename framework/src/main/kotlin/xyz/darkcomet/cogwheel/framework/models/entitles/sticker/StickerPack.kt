@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.sticker

import xyz.darkcomet.cogwheel.core.network.objects.StickerPackObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.StickerId
import xyz.darkcomet.cogwheel.framework.primitives.StickerPackId
import xyz.darkcomet.cogwheel.framework.primitives.asSkuId
import xyz.darkcomet.cogwheel.framework.primitives.asStickerId
import xyz.darkcomet.cogwheel.framework.primitives.asStickerPackId

class StickerPack(
    val id: StickerPackId,
    val stickers: List<Sticker>,
    val name: String,
    val skuId: SkuId,
    val coverStickerId: StickerId?,
    val description: String,
    val bannerAssetId: Snowflake?
) {
    companion object {
        internal fun from(obj: StickerPackObject): StickerPack {
            return obj.toModel()
        }
    }
}

internal fun StickerPackObject.toModel(): StickerPack {
    return StickerPack(
        id = requireNonNull(this, StickerPackObject::id).asStickerPackId(),
        stickers = requireNonNull(this, StickerPackObject::stickers).map { it.toModel() },
        name = requireNonNull(this, StickerPackObject::name),
        skuId = requireNonNull(this, StickerPackObject::skuId).asSkuId(),
        coverStickerId = requireNonNullIfPresent(this, StickerPackObject::coverStickerId)?.asStickerId(),
        description = requireNonNull(this, StickerPackObject::description),
        bannerAssetId = requireNonNullIfPresent(this, StickerPackObject::bannerAssetId)
    )
}