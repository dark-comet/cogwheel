package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyThreadChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class ModifyThreadChannelRequestSpec(
    internal val channelId: ChannelId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String): ModifyThreadChannelRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): ModifyThreadChannelRequestParameters {
        TODO()
    }
}