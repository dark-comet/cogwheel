package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.SendSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.SoundboardSoundId

class SendSoundboardSoundRequestSpec(internal val channelId: ChannelId) {
    
    internal var soundId: SoundboardSoundId? = null
    internal var sourceGuildId: GuildId? = null
    
    internal fun buildParameters(): SendSoundboardSoundRequestParameters {
        val sourceGuildId = this.sourceGuildId
        
        return SendSoundboardSoundRequestParameters(
            soundId = this.soundId?.snowflake ?: throw InvalidModelException("'soundId' is required"),
            sourceGuildId = if (sourceGuildId != null) MaybeAbsent(sourceGuildId.snowflake) else null
        )
    }
}