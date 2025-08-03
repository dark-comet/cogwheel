@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.sticker

import xyz.darkcomet.cogwheel.core.network.objects.StickerObject
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.StickerFormatType
import xyz.darkcomet.cogwheel.framework.primitives.StickerId
import xyz.darkcomet.cogwheel.framework.primitives.StickerPackId
import xyz.darkcomet.cogwheel.framework.primitives.StickerType
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId
import xyz.darkcomet.cogwheel.framework.primitives.asStickerId
import xyz.darkcomet.cogwheel.framework.primitives.asStickerPackId

class Sticker(
    id: StickerId,
    val packId: StickerPackId?,
    name: String,
    val description: String?,
    val tags: List<String>,
    val type: StickerType,
    formatType: StickerFormatType,
    val available: Boolean?,
    val guildId: GuildId?,
    val user: User?,
    val sortValue: Int?
) : PartialSticker(id, name, formatType) {
    companion object {
        internal fun from(obj: StickerObject): Sticker {
            return obj.toModel()
        }
    }
}

internal fun StickerObject.toModel(): Sticker {
    return Sticker(
        id = requireNonNull(this, StickerObject::id).asStickerId(),
        packId = requireNonNullIfPresent(this, StickerObject::packId)?.asStickerPackId(),
        name = requireNonNull(this, StickerObject::name),
        description = require(this, StickerObject::description),
        tags = requireNonNull(this, StickerObject::tags).split(","),
        type = requireNonNull(this, StickerObject::type).let { StickerType.fromOrAdd(it) },
        formatType = requireNonNull(this, StickerObject::formatType).let { StickerFormatType.fromOrAdd(it) },
        available = requireNonNullIfPresent(this, StickerObject::available),
        guildId = requireNonNullIfPresent(this, StickerObject::guildId)?.asGuildId(),
        user = requireNonNullIfPresent(this, StickerObject::user)?.toModel(),
        sortValue = requireNonNullIfPresent(this, StickerObject::sortValue),
    )
}