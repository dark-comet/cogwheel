package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.EditChannelPermissionsParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class EditChannelPermissionsSpec(
    internal val channelId: ChannelId,
    internal val overwriteId: Snowflake
) {
    internal var auditLogReason: String? = null 
    
    fun auditLogReason(reason: String?): EditChannelPermissionsSpec 
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): EditChannelPermissionsParameters {
        TODO()
    }
}