package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.RoleId

class CreateGuildEmojiRequestSpec(internal val guildId: GuildId) {
    
    internal var name: String? = null
    internal var image: String? = null
    internal var roles: List<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): CreateGuildEmojiRequestSpec 
        = apply { this.name = name }
    
    fun image(image: DiscordImage): CreateGuildEmojiRequestSpec
        = apply { this.image = image.data.toString() }
    
    fun roles(roles: List<RoleId>): CreateGuildEmojiRequestSpec
        = apply { this.roles = roles.map { it.snowflake } }
    
    fun roles(vararg roles: RoleId): CreateGuildEmojiRequestSpec
        = apply { this.roles = roles.toList().map { it.snowflake } }
    
    fun auditLogReason(reason: String): CreateGuildEmojiRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): CreateGuildEmojiRequestParameters {
        return CreateGuildEmojiRequestParameters(
            name = name ?: throw InvalidModelException("'name' is required"),
            image = image ?: throw InvalidModelException("'image' is required"),
            roles = roles ?: throw InvalidModelException("'roles' is required"),
        )
    }
}