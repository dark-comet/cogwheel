package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.RoleId

class ModifyGuildEmojiRequestSpec(
    internal val guildId: GuildId, 
    internal val emojiId: EmojiId
) {
    internal var name: String? = null
    internal var roles: List<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildEmojiRequestSpec
        = apply { this.name = name }
    
    fun roles(roles: List<RoleId>): ModifyGuildEmojiRequestSpec 
        = apply { this.roles = roles.map { it.snowflake } }
    
    fun roles(vararg roles: RoleId): ModifyGuildEmojiRequestSpec
        = apply { this.roles = roles.asList().map { it.snowflake } }
    
    fun auditLogReason(reason: String): ModifyGuildEmojiRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): ModifyGuildEmojiRequestParameters {
        return ModifyGuildEmojiRequestParameters(
            name = MaybeAbsent(this.name ?: throw InvalidModelException("'name' is required")),
            roles = MaybeAbsent(this.roles)
        )
    }
}