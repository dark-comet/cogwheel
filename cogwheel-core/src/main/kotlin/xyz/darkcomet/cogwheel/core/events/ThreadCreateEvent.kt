package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject

class ThreadCreateEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>