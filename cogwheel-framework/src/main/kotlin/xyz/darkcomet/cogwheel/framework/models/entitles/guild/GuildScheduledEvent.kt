package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventObject

// TODO
class GuildScheduledEvent() {
    companion object {
        internal fun from(obj: GuildScheduledEventObject): GuildScheduledEvent {
            return obj.toModel()
        }
    }
}

internal fun GuildScheduledEventObject.toModel(): GuildScheduledEvent {
    return GuildScheduledEvent()
}