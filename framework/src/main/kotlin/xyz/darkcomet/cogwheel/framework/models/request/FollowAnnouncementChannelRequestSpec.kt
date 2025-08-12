@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.FollowAnnouncementChannelRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class FollowAnnouncementChannelRequestSpec(
    internal val channelId: ChannelId
) {
    private var webhookChannelId: Snowflake? = null
            
    internal var auditLogReason: String? = null
    
    fun webhookChannelId(id: ChannelId): FollowAnnouncementChannelRequestSpec
        = apply { this.webhookChannelId = id.snowflake }
    
    fun auditLogReason(reason: String?): FollowAnnouncementChannelRequestSpec
        = apply { auditLogReason = reason }
    
    fun buildParameters(): FollowAnnouncementChannelRequestParameters {
        return FollowAnnouncementChannelRequestParameters(
            webhookChannelId = this.webhookChannelId ?: throw InvalidModelException("'webhookChannelId' is required")
        )
    }
}