package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.entities.GatewayReadyEventDataEntity

class GatewayReadyEvent internal constructor(val data: GatewayReadyEventDataEntity) : Event {
}