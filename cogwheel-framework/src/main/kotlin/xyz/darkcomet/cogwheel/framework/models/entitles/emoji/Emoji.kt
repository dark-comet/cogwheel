package xyz.darkcomet.cogwheel.framework.models.entitles.emoji

import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.RoleId
import xyz.darkcomet.cogwheel.framework.primitives.asEmojiId
import xyz.darkcomet.cogwheel.framework.primitives.asRoleId

class Emoji(
    val id: EmojiId?,
    val name: String?,
    val roles: List<RoleId>?,
    val user: User?,
    val requireColons: Boolean?,
    val managed: Boolean?,
    val animated: Boolean?,
    val available: Boolean?,
) {
    companion object {
        internal fun from(obj: EmojiObject): Emoji {
            return obj.toModel()
        }
    }
}

internal fun EmojiObject.toModel(): Emoji {
    return Emoji(
        id = require(this, EmojiObject::id)?.asEmojiId(),
        name = require(this, EmojiObject::name),
        roles = requireNonNullIfPresent(this, EmojiObject::roles)?.map { it.asRoleId() },
        user = requireNonNullIfPresent(this, EmojiObject::user)?.toModel(),
        requireColons = requireNonNullIfPresent(this, EmojiObject::requireColons),
        managed = requireNonNullIfPresent(this, EmojiObject::managed),
        animated = requireNonNullIfPresent(this, EmojiObject::animated),
        available = requireNonNullIfPresent(this, EmojiObject::available),
    )
}