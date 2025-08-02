package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyUserVoiceStateRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ModifyUserVoiceStateRequestSpec(
    internal val guildId: GuildId,
    internal val userId: UserId, 
) {
    internal var channelId: Snowflake? = null
    internal var suppress: MaybeAbsent<Boolean>? = null
    
    fun channelId(id: ChannelId): ModifyUserVoiceStateRequestSpec
        = apply { this.channelId = id.snowflake }
    
    fun suppress(flag: Boolean): ModifyUserVoiceStateRequestSpec
        = apply { this.suppress = MaybeAbsent(flag) }
    
    internal fun buildParameters(): ModifyUserVoiceStateRequestParameters {
        return ModifyUserVoiceStateRequestParameters(
            channelId = this.channelId ?: throw InvalidModelException("'channelId' is required"),
            suppress = this.suppress
        )
    }
}