@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles

import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.*

class SoundboardSound(
    val name: String,
    val soundId: SoundboardSoundId,
    val volume: Double,
    val emojiId: EmojiId?,
    val emojiName: String?,
    val guildId: GuildId?,
    val available: Boolean,
    val user: User?
) {
    companion object {
        internal fun from(obj: SoundboardSoundObject): SoundboardSound {
            return obj.toModel()
        }
    }
}

internal fun SoundboardSoundObject.toModel(): SoundboardSound {
    return SoundboardSound(
        name = requireNonNull(this, SoundboardSoundObject::name),
        soundId = requireNonNull(this, SoundboardSoundObject::soundId).asSoundboardSoundId(),
        volume = requireNonNull(this, SoundboardSoundObject::volume),
        emojiId = require(this, SoundboardSoundObject::emojiId)?.asEmojiId(),
        emojiName = require(this, SoundboardSoundObject::emojiName),
        guildId = requireNonNullIfPresent(this, SoundboardSoundObject::guildId)?.asGuildId(),
        available = requireNonNull(this, SoundboardSoundObject::available),
        user = requireNonNullIfPresent(this, SoundboardSoundObject::user)?.toModel()
    )
}