package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.GroupDmAddRecipientRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GroupDmAddRecipientRequestSpec(
    internal val channelId: ChannelId,
    internal val userId: UserId
) {
    internal var auditLogReason: String? = null
    
    fun auditLogReason(reason: String?): GroupDmAddRecipientRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): GroupDmAddRecipientRequestParameters {
        TODO()
    }
}