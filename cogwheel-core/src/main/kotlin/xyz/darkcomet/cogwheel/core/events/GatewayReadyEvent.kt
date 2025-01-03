package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GatewayReadyEventDataObject

class GatewayReadyEvent internal constructor(val data: GatewayReadyEventDataObject) : Event {
}