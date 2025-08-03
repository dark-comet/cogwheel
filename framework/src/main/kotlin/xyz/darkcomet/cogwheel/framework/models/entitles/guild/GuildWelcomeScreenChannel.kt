package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildWelcomeScreenChannelObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId
import xyz.darkcomet.cogwheel.framework.primitives.asEmojiId

data class GuildWelcomeScreenChannel(
    val channelId: ChannelId,
    val description: String,
    val emojiId: EmojiId,
    val emojiName: String
) {
    companion object {
        internal fun from(obj: GuildWelcomeScreenChannelObject): GuildWelcomeScreenChannel {
            return obj.toModel()
        }
    }
}

internal fun GuildWelcomeScreenChannelObject.toModel(): GuildWelcomeScreenChannel {
    return GuildWelcomeScreenChannel(
        channelId = requireNonNull(this, GuildWelcomeScreenChannelObject::channelId).asChannelId(),
        description = requireNonNull(this, GuildWelcomeScreenChannelObject::description),
        emojiId = requireNonNull(this, GuildWelcomeScreenChannelObject::emojiId).asEmojiId(),
        emojiName = requireNonNull(this, GuildWelcomeScreenChannelObject::emojiName)
    )
}