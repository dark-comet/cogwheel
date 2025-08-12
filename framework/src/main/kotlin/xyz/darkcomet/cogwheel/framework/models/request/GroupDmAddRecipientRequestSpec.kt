@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.GroupDmAddRecipientRequestParameters
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GroupDmAddRecipientRequestSpec(
    internal val channelId: ChannelId,
    internal val userId: UserId
) {
    private var accessToken: String? = null
    private var nick: String? = null
    
    internal var auditLogReason: String? = null
    
    fun accessToken(token: String): GroupDmAddRecipientRequestSpec
        = apply { this.accessToken = token }
    
    fun nick(nickname: String): GroupDmAddRecipientRequestSpec
        = apply { this.nick = nickname }
    
    fun auditLogReason(reason: String?): GroupDmAddRecipientRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): GroupDmAddRecipientRequestParameters {
        return GroupDmAddRecipientRequestParameters(
            accessToken = this.accessToken ?: throw InvalidModelException("'accessToken' must not be null"),
            nick = this.nick ?: throw InvalidModelException("'nick' must not be null"),
        )
    }
}