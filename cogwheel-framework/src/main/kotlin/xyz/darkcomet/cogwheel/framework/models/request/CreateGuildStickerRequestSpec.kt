package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.DiscordFileContent
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class CreateGuildStickerRequestSpec(
    internal val guildId: GuildId
) {
    
    internal var name: String? = null
    internal var description: String? = null
    internal var tags: String? = null
    internal var file: String? = null // TODO: what does 'file contents' type map to?
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): CreateGuildStickerRequestSpec
        = apply { this.name = name }
    
    fun description(description: String): CreateGuildStickerRequestSpec
        = apply { this.description = description }
    
    fun tags(vararg tags: String): CreateGuildStickerRequestSpec
        = apply { this.tags = if (tags.size > 1) tags.joinToString(",") else tags.firstOrNull() }
    
    fun file(content: DiscordFileContent): CreateGuildStickerRequestSpec
        = apply { this.file = content.toString() }
    
    fun auditLogReason(reason: String?): CreateGuildStickerRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): CreateGuildStickerRequestParameters {
        return CreateGuildStickerRequestParameters(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            description = this.description ?: throw InvalidModelException("'description' is required"),
            tags = this.tags ?: throw InvalidModelException("'tags' is required"),
            file = this.file ?: throw InvalidModelException("'file' is required"),
        )
    }
    
}