package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.StartThreadWithoutMessageRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class StartThreadWithoutMessageRequestSpec(
    internal val channelId: ChannelId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): StartThreadWithoutMessageRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadWithoutMessageRequestParameters {
        TODO()
    }
}