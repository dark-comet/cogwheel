package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.DiscordSound
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.SoundboardSoundId

class ModifyGuildSoundboardSoundRequestSpec(
    internal val guildId: GuildId,
    internal val soundId: SoundboardSoundId
) {
    internal var name: MaybeAbsent<String>? = null
    internal var sound: MaybeAbsent<String>? = null
    internal var volume: MaybeAbsent<Double?>? = null
    internal var emojiId: MaybeAbsent<Snowflake?>? = null
    internal var emojiName: MaybeAbsent<String?>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.name = MaybeAbsent(name) }
    
    fun sound(sound: DiscordSound): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.sound = MaybeAbsent(sound.toString()) }
    
    fun volume(volume: Double?): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.volume = MaybeAbsent(volume) }
    
    fun emojiId(id: EmojiId?): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.emojiId = MaybeAbsent(id?.snowflake) }
    
    fun emojiName(name: String?): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.emojiName = MaybeAbsent(name) }
    
    fun auditLogReason(reason: String?): ModifyGuildSoundboardSoundRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): ModifyGuildSoundboardSoundRequestParameters {
        return ModifyGuildSoundboardSoundRequestParameters(
            name = this.name,
            sound = this.sound,
            volume = this.volume,
            emojiId = this.emojiId,
            emojiName = this.emojiName
        )
    }
}