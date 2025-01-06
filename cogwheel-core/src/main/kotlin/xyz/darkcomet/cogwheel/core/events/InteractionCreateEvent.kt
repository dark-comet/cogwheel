package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.InteractionObject

class InteractionCreateEvent
internal constructor(override val data: InteractionObject) : Event<InteractionObject>