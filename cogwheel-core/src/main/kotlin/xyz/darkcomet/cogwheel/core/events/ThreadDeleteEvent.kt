package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject

class ThreadDeleteEvent
internal constructor(override val data: ChannelObject): Event<ChannelObject>