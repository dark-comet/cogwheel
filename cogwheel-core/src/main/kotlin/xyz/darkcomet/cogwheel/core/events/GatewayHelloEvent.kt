package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GatewayHelloEventDataEntity

class GatewayHelloEvent 
internal constructor(val data: GatewayHelloEventDataEntity) : Event {
    
}