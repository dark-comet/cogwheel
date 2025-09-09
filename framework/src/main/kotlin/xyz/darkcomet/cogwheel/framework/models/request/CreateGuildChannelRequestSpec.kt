package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class CreateGuildChannelRequestSpec(
    internal val guildId: GuildId
) {
    internal var auditLogReason: String? = null
    
    internal fun buildParameters(): CreateGuildChannelRequestParameters {
        TODO()
    }
}