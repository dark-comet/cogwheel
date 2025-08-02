package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyCurrentUserVoiceStateRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class ModifyCurrentUserVoiceStateRequestSpec(
    internal val guildId: GuildId,
) {
    internal var channelId: MaybeAbsent<Snowflake>? = null
    internal var suppress: MaybeAbsent<Boolean>? = null
    internal var requestToSpeakTimestamp: MaybeAbsent<ISO8601Timestamp?>? = null
    
    fun channelId(id: ChannelId): ModifyCurrentUserVoiceStateRequestSpec
        = apply { this.channelId = MaybeAbsent(id.snowflake) }
    
    fun suppress(flag: Boolean): ModifyCurrentUserVoiceStateRequestSpec
        = apply { this.suppress = MaybeAbsent(flag) }
    
    fun requestToSpeakTimestamp(timestamp: ISO8601Timestamp?): ModifyCurrentUserVoiceStateRequestSpec
        = apply { this.requestToSpeakTimestamp = MaybeAbsent(timestamp) }
    
    internal fun buildParameters(): ModifyCurrentUserVoiceStateRequestParameters {
        return ModifyCurrentUserVoiceStateRequestParameters(
            channelId = this.channelId,
            suppress = this.suppress,
            requestToSpeakTimestamp = this.requestToSpeakTimestamp
        )
    }
}