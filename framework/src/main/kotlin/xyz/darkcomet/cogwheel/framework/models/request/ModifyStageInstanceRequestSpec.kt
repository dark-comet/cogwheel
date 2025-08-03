package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.StageInstancePrivacyLevel

class ModifyStageInstanceRequestSpec(internal var channelId: ChannelId) {
    
    internal var topic: String? = null
    internal var privacyLevel: Int? = null
    
    internal var auditLogReason: String? = null
    
    fun topic(topic: String): ModifyStageInstanceRequestSpec
        = apply { this.topic = topic }
    
    fun privacyLevel(privacyLevel: StageInstancePrivacyLevel): ModifyStageInstanceRequestSpec
        = apply { this.privacyLevel = privacyLevel.key }
    
    fun auditLogReason(reason: String): ModifyStageInstanceRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): ModifyStageInstanceRequestParameters {
        return ModifyStageInstanceRequestParameters(
            topic = if(topic != null) MaybeAbsent(topic) else null,
            privacyLevel = if(privacyLevel != null) MaybeAbsent(privacyLevel) else null,
        )
    } 
}