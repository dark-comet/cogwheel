@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.StickerId

class DeleteGuildStickerRequestSpec(
    internal val guildId: GuildId,
    internal val stickerId: StickerId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): DeleteGuildStickerRequestSpec
        = apply { this.auditLogReason = reason }
    
}