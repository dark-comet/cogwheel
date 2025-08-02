@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.StickerId

class ModifyGuildStickerRequestSpec(
    internal val guildId: GuildId,
    internal val stickerId: StickerId
) {
    internal var name: MaybeAbsent<String>? = null
    internal var description: MaybeAbsent<String?>? = null
    internal var tags: MaybeAbsent<String>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildStickerRequestSpec
        = apply { this.name = MaybeAbsent(name) }
    
    fun description(description: String?): ModifyGuildStickerRequestSpec
        = apply { this.description = MaybeAbsent(description) }
    
    fun tags(vararg tags: String): ModifyGuildStickerRequestSpec {
        val value = if (tags.size > 1) tags.joinToString(",") else tags.firstOrNull()
        this.tags = MaybeAbsent(value)
        
        return this
    }
    
    fun auditLogReason(reason: String?): ModifyGuildStickerRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): ModifyGuildStickerRequestParameters {
        return ModifyGuildStickerRequestParameters(
            name = this.name,
            description = this.description,
            tags = this.tags,
        )
    }
}