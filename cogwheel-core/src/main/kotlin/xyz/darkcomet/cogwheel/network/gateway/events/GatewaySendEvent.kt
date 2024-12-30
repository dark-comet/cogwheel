package xyz.darkcomet.cogwheel.network.gateway.events

import xyz.darkcomet.cogwheel.network.gateway.GatewayPayload

interface GatewaySendEvent {
    fun asPayload() : GatewayPayload
}