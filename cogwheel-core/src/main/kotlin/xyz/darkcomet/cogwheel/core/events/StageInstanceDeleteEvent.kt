package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.StageInstanceObject

class StageInstanceDeleteEvent
internal constructor(override val data: StageInstanceObject) : Event<StageInstanceObject>
