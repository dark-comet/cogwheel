package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyDmChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class ModifyDmChannelRequestSpec(
    internal val channelId: ChannelId,
) {
    internal fun buildParameters(): ModifyDmChannelRequestParameters {
        TODO()
    }
}