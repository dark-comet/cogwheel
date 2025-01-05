package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.request.GatewayRequestSoundboardSoundsRequestParameters

internal class GatewayRequestSoundboardSoundsSendEvent(
    private val request: GatewayRequestSoundboardSoundsRequestParameters
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        return GatewayPayload(
            op = GatewayOpCode.REQUEST_SOUNDBOARD_SOUNDS.code,
            d = Json.encodeToJsonElement(request)
        )
    }

}