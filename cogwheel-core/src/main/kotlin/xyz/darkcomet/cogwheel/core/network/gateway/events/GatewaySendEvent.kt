package xyz.darkcomet.cogwheel.core.network.gateway.events

import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload

internal interface GatewaySendEvent {
    fun asPayload() : GatewayPayload
}