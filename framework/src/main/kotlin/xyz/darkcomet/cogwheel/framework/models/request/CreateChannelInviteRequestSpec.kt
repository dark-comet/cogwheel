package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateChannelInviteRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class CreateChannelInviteRequestSpec(
    internal val channelId: ChannelId,
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): CreateChannelInviteRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): CreateChannelInviteRequestParameters {
        TODO()
    }
}