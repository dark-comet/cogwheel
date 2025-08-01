package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class DeleteStageInstanceRequestSpec(internal var channelId: ChannelId) {
    
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): DeleteStageInstanceRequestSpec
        = apply { auditLogReason = reason }
    
}