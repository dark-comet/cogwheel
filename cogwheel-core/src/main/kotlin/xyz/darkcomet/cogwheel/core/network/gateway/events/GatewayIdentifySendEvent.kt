package xyz.darkcomet.cogwheel.core.network.gateway.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.primitives.Intents
import xyz.darkcomet.cogwheel.core.network.gateway.GatewayPayload
import xyz.darkcomet.cogwheel.core.network.gateway.codes.GatewayOpCode
import xyz.darkcomet.cogwheel.core.network.objects.UpdatePresenceObject

internal class GatewayIdentifySendEvent(
    private val token: Token,
    private val intents: Intents,
    private val libName: String
) : GatewaySendEvent {
    
    override fun asPayload(): GatewayPayload {
        val data = DataObject(
            token = token.value,
            properties = DataObject.ConnectionPropertiesObject(
                os = System.getProperty("os.name"),
                browser = libName,
                device = libName
            ),
            intents = intents.value
        )

        return GatewayPayload(
            op = GatewayOpCode.IDENTIFY.code, 
            d = Json.encodeToJsonElement(DataObject.serializer(), data)
        )
    }

    @Serializable
    data class DataObject(
        val token: String,
        val properties: ConnectionPropertiesObject,
        val compress: Boolean? = null,
        @SerialName("large_threshold") val largeThreshold: Int? = null,
        val shard: List<Int>? = null,
        val presence: UpdatePresenceObject? = null,
        val intents: Int
    ) {
        
        @Serializable
        data class ConnectionPropertiesObject(
            val os: String,
            val browser: String,
            val device: String
        )
        
    }
    
}