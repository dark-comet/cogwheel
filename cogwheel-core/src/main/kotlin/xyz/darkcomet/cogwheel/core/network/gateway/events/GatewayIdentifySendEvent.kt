package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.primitives.Intents
import xyz.darkcomet.cogwheel.core.network.objects.GatewayIdentifyEventDataObject
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode

internal class GatewayIdentifySendEvent(
    private val token: Token,
    private val intents: Intents,
    private val libName: String
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        val data = GatewayIdentifyEventDataObject(
            token = token.value,
            properties = GatewayIdentifyEventDataObject.IdentifyConnectionPropertiesObject(
                os = System.getProperty("os.name"),
                browser = libName,
                device = libName
            ),
            intents = intents.value
        )

        return GatewayPayload(
            op = GatewayOpCode.IDENTIFY.code, 
            d = Json.encodeToJsonElement(GatewayIdentifyEventDataObject.serializer(), data)
        )
    }
    
}