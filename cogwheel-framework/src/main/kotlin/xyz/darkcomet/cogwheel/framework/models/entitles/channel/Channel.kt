package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject

class Channel {
    companion object {
        internal fun from(obj: ChannelObject): Channel {
            return obj.toModel()
        }
    }
}

internal fun ChannelObject.toModel(): Channel {
    return Channel()
}