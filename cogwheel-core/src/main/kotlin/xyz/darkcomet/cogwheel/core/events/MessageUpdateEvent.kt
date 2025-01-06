package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.MessageObject

class MessageUpdateEvent
internal constructor(override val data: MessageObject) : Event<MessageObject>