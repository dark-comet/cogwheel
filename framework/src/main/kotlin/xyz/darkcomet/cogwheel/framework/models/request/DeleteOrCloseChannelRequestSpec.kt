package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class DeleteOrCloseChannelRequestSpec(
    internal val channelId: ChannelId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): DeleteOrCloseChannelRequestSpec 
        = apply { this.auditLogReason = reason }
}