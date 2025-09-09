package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

// TODO: Complete me
class ModifyGuildRequestSpec(internal val guildId: GuildId) {
    
    internal var auditLogReason: String? = null
    
    internal fun buildParameters(): ModifyGuildRequestParameters {
        TODO()
    }
}