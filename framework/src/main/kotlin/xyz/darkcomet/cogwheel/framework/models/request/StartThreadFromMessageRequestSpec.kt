package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.StartThreadFromMessageRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.MessageId

class StartThreadFromMessageRequestSpec(
    val channelId: ChannelId,
    val messageId: MessageId,
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): StartThreadFromMessageRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): StartThreadFromMessageRequestParameters {
        TODO()
    }
}