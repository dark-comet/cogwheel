package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayVoiceStateUpdateRequestParameters

internal class GatewayUpdateVoiceStateSendEvent(
    private val request: GatewayVoiceStateUpdateRequestParameters
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.VOICE_STATE_UPDATE.code,
            d = Json.encodeToJsonElement(request)
        )
    }
    
}