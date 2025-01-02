package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.entities.GatewayHelloEventDataEntity

class GatewayHelloEvent 
internal constructor(val data: GatewayHelloEventDataEntity) : Event {
    
}