@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ChannelDefaultReactionObject
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.asEmojiId

class ChannelDefaultReaction(
    val emojiId: EmojiId?,
    val emojiName: String?
) {
    companion object {
        internal fun from(obj: ChannelDefaultReactionObject): ChannelDefaultReaction {
            return obj.toModel()
        }
    }
}

internal fun ChannelDefaultReactionObject.toModel(): ChannelDefaultReaction {
    return ChannelDefaultReaction(
        emojiId = require(this, ChannelDefaultReactionObject::emojiId)?.asEmojiId(),
        emojiName = require(this, ChannelDefaultReactionObject::emojiName),
    )
}