@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ForumTagObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.ForumTagId
import xyz.darkcomet.cogwheel.framework.primitives.asEmojiId
import xyz.darkcomet.cogwheel.framework.primitives.asForumTagId

class ForumTag(
    val id: ForumTagId,
    val name: String,
    val moderated: Boolean,
    val emojiId: EmojiId?,
    val emojiName: String?
) {
    companion object {
        internal fun from(obj: ForumTagObject): ForumTag {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): ForumTagObject {
        return ForumTagObject(
            id = MaybeAbsent(this.id.snowflake),
            name = MaybeAbsent(this.name),
            moderated = MaybeAbsent(this.moderated),
            emojiId = MaybeAbsent(this.emojiId?.snowflake),
            emojiName = MaybeAbsent(this.emojiName),
        )
    }
}

internal fun ForumTagObject.toModel(): ForumTag {
    return ForumTag(
        id = requireNonNull(this, ForumTagObject::id).asForumTagId(),
        name = requireNonNull(this, ForumTagObject::name),
        moderated = requireNonNull(this, ForumTagObject::moderated),
        emojiId = require(this, ForumTagObject::emojiId)?.asEmojiId(),
        emojiName = require(this, ForumTagObject::emojiName)
    )
}