@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyDmChannelRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.DiscordImage

class ModifyDmChannelRequestSpec(
    internal val channelId: ChannelId,
) {
    internal var name: MaybeAbsent<String>? = null
    internal var icon: MaybeAbsent<String>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyDmChannelRequestSpec 
        = apply { this.name = MaybeAbsent(name) }
    
    fun icon(icon: DiscordImage): ModifyDmChannelRequestSpec
        = apply { this.icon = MaybeAbsent(icon.data.toString()) }
    
    fun auditLogReason(reason: String): ModifyDmChannelRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): ModifyDmChannelRequestParameters {
        return ModifyDmChannelRequestParameters(
            name = name,
            icon = icon
        )
    }
}