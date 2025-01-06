package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode

internal class GatewayResumeSendEvent(
    private val token: Token,
    private val sessionId: String,
    private val lastReceivedSequenceNumber: Int,
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        val data = DataObject(token.value, sessionId, lastReceivedSequenceNumber)
        
        return GatewayPayload(
            op = GatewayOpCode.RESUME.code, 
            d = Json.encodeToJsonElement(DataObject.serializer(), data)
        )
    }

    @Serializable
    data class DataObject(
        val token: String,
        @SerialName("session_id") val sessionId: String,
        val seq: Int
    )
    
}