package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayPresenceUpdateRequestParameters

internal class GatewayUpdatePresenceSendEvent(
    private val request: GatewayPresenceUpdateRequestParameters
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.PRESENCE_UPDATE.code,
            d = Json.encodeToJsonElement(request)
        )
    }
    
}