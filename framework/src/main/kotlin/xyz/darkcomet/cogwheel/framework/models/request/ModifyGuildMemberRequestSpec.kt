package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildMemberRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.*

class ModifyGuildMemberRequestSpec(
    internal val guildId: GuildId,
    internal val userId: UserId,
) {
    internal var nickname: String? = null
    internal var roles: List<Snowflake>? = mutableListOf()
    internal var mute: Boolean? = null
    internal var deaf: Boolean? = null
    internal var channelId: Snowflake? = null
    internal var communicationDisabledUntil: ISO8601Timestamp? = null
    internal var flags: Int? = null

    fun nickname(value: String): ModifyGuildMemberRequestSpec
        = apply { this.nickname = value }

    fun roles(roles: List<RoleId>): ModifyGuildMemberRequestSpec
        = apply { this.roles = roles.map { it.snowflake } }

    fun mute(value: Boolean): ModifyGuildMemberRequestSpec
        = apply { this.mute = value }

    fun deaf(value: Boolean): ModifyGuildMemberRequestSpec
        = apply { this.deaf = value }
    
    fun channelId(id: ChannelId): ModifyGuildMemberRequestSpec
        = apply { this.channelId = id.snowflake }
    
    fun communicationDisabledUntil(timeUntil: ISO8601Timestamp): ModifyGuildMemberRequestSpec
        = apply { this.communicationDisabledUntil = timeUntil }

    fun flags(flags: BitField<GuildMemberFlag>): ModifyGuildMemberRequestSpec
        = apply { this.flags = flags.value.toInt() }
    
    fun flags(vararg flags: GuildMemberFlag): ModifyGuildMemberRequestSpec
        = apply { this.flags = BitField.from(*flags).value.toInt() }
    
    internal fun buildParameters(): ModifyGuildMemberRequestParameters {

        return ModifyGuildMemberRequestParameters(
            nick = if (this.nickname != null) MaybeAbsent(this.nickname) else null,
            roles = if (this.roles != null) MaybeAbsent(this.roles) else null,
            mute = if (this.mute != null) MaybeAbsent(this.mute) else null,
            deaf = if (this.deaf != null) MaybeAbsent(this.deaf) else null,
            channelId = if (this.channelId != null) MaybeAbsent(this.channelId) else null,
            communicationDisabledUntil = if (this.communicationDisabledUntil != null) MaybeAbsent(this.communicationDisabledUntil) else null,
            flags = if (this.flags != null) MaybeAbsent(flags) else null, 
        )
    }
    
}