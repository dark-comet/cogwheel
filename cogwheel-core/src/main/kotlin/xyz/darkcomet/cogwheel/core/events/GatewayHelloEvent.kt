package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GatewayHelloEventDataObject

class GatewayHelloEvent 
internal constructor(val data: GatewayHelloEventDataObject) : Event {
    
}