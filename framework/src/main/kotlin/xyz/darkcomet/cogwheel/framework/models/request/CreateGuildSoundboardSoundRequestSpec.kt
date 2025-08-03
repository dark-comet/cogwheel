package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.DiscordSound
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class CreateGuildSoundboardSoundRequestSpec(
    internal val guildId: GuildId
) {
    internal var name: String? = null
    internal var sound: String? = null
    internal var volume: MaybeAbsent<Double?>? = null
    internal var emojiId: MaybeAbsent<Snowflake?>? = null
    internal var emojiName: MaybeAbsent<String?>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): CreateGuildSoundboardSoundRequestSpec
        = apply { this.name = name }
    
    fun sound(sound: DiscordSound): CreateGuildSoundboardSoundRequestSpec
        = apply { this.sound = sound.toString() }
    
    fun volume(volume: Double?): CreateGuildSoundboardSoundRequestSpec
        = apply { this.volume = MaybeAbsent(volume) }
    
    fun emojiId(id: EmojiId?): CreateGuildSoundboardSoundRequestSpec
        = apply { if (id != null) MaybeAbsent(id) else null }
    
    fun emojiName(name: String?): CreateGuildSoundboardSoundRequestSpec
        = apply { if (name != null) MaybeAbsent(name) else null }
    
    fun auditLogReason(reason: String?): CreateGuildSoundboardSoundRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): CreateGuildSoundboardSoundRequestParameters {
        return CreateGuildSoundboardSoundRequestParameters(
            name = name ?: throw InvalidModelException("'name' is required"),
            sound = sound ?: throw InvalidModelException("'sound' is required"),
            volume = this.volume,
            emojiId = this.emojiId,
            emojiName = this.emojiName
        )
    }
}