package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.AddGuildMemberRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.RoleId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class AddGuildMemberRequestSpec(
    internal val guildId: GuildId,
    internal val userId: UserId,
) {
    internal var accessToken: String? = null
    internal var nickname: String? = null
    internal var roles: List<Snowflake>? = mutableListOf()
    internal var mute: Boolean? = null
    internal var deaf: Boolean? = null
    
    fun accessToken(value: String): AddGuildMemberRequestSpec
        = apply { this.accessToken = value }
    
    fun nickname(value: String): AddGuildMemberRequestSpec
        = apply { this.nickname = value }
    
    fun roles(roles: List<RoleId>): AddGuildMemberRequestSpec
        = apply { this.roles = roles.map { it.snowflake } }
    
    fun mute(value: Boolean): AddGuildMemberRequestSpec
        = apply { this.mute = value }
    
    fun deaf(value: Boolean): AddGuildMemberRequestSpec
        = apply { this.deaf = value }
    
    internal fun buildParameters(): AddGuildMemberRequestParameters {
        
        return AddGuildMemberRequestParameters(
            accessToken = this.accessToken ?: throw InvalidModelException("'accessToken' is required"),
            nick = if (this.nickname != null) MaybeAbsent(this.nickname) else null,
            roles = if (this.roles != null) MaybeAbsent(this.roles) else null,
            mute = if (this.mute != null) MaybeAbsent(this.mute) else null,
            deaf = if (this.deaf != null) MaybeAbsent(this.deaf) else null
        )
    }
}