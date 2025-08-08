package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.FollowAnnouncementChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class FollowAnnouncementChannelRequestSpec(
    internal val channelId: ChannelId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): FollowAnnouncementChannelRequestSpec
        = apply { auditLogReason = reason }
    
    fun buildParameters(): FollowAnnouncementChannelRequestParameters {
        TODO()
    }
}