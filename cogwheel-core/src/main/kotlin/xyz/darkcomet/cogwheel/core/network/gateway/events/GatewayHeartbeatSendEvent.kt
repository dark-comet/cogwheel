package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode

internal class GatewayHeartbeatSendEvent(private val lastReceivedSequenceNumber: Int) :
    GatewaySendEvent {
    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.HEARTBEAT.code,
            d = Json.encodeToJsonElement(lastReceivedSequenceNumber)
        )
    }
}