@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.SingleFileSupplier
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import java.nio.file.Path

class CreateGuildStickerRequestSpec(
    internal val guildId: GuildId
) {
    
    internal var name: String? = null
    internal var description: String? = null
    internal var tags: String? = null
    internal var file: Path? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): CreateGuildStickerRequestSpec
        = apply { this.name = name }
    
    fun description(description: String): CreateGuildStickerRequestSpec
        = apply { this.description = description }
    
    fun tags(vararg tags: String): CreateGuildStickerRequestSpec
        = apply { this.tags = if (tags.size > 1) tags.joinToString(",") else tags.firstOrNull() }
    
    fun tags(tags: Collection<String>): CreateGuildStickerRequestSpec
        = apply { this.tags = if (tags.size > 1) tags.joinToString(",") else tags.firstOrNull() }
    
    fun file(file: Path): CreateGuildStickerRequestSpec
        = apply { this.file = file }
    
    fun auditLogReason(reason: String?): CreateGuildStickerRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): CreateGuildStickerRequestParameters {
        val jsonPayload = CreateGuildStickerRequestParameters.PayloadJson(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            description = this.description ?: throw InvalidModelException("'description' is required"),
            tags = this.tags ?: throw InvalidModelException("'tags' is required"),
        )
        val file = SingleFileSupplier(this.file ?: throw InvalidModelException("'file' is required"))
        
        return CreateGuildStickerRequestParameters(jsonPayload, file)
    }
    
}