package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.network.objects.GatewayResumeEventDataObject
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode

class GatewayResumeSendEvent(
    private val token: Token,
    private val sessionId: String,
    private val lastReceivedSequenceNumber: Int,
) : GatewaySendEvent {
    override fun asPayload(): GatewayPayload {
        val data = GatewayResumeEventDataObject(token.value, sessionId, lastReceivedSequenceNumber)
        
        return GatewayPayload(
            op = GatewayOpCode.RESUME.code, 
            d = Json.encodeToJsonElement(GatewayResumeEventDataObject.serializer(), data)
        )
    }
}