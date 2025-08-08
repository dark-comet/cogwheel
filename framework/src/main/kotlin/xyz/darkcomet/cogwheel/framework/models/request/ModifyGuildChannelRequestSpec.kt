package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildChannelRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class ModifyGuildChannelRequestSpec(
    internal val channelId: ChannelId
) {
    internal fun buildParameters(): ModifyGuildChannelRequestParameters {
        TODO()
    }
}