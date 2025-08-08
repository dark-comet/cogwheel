package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.StartThreadInForumOrMediaChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class StartThreadInForumOrMediaChannelRequestSpec(
    internal val channelId: ChannelId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): StartThreadInForumOrMediaChannelRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadInForumOrMediaChannelRequestParameters {
        TODO()
    }
}