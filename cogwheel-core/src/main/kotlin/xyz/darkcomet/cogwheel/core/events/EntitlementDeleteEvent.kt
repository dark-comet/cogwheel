package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.EntitlementObject

class EntitlementDeleteEvent
internal constructor(override val data: EntitlementObject) : Event<EntitlementObject>