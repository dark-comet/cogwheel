package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventObject

class GuildScheduledEventUpdateEvent
internal constructor(override val data: GuildScheduledEventObject) : Event<GuildScheduledEventObject>
