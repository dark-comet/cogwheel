package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class GatewayHelloEvent 
internal constructor(override val data: DataObject) : Event<GatewayHelloEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("heartbeat_interval") val heartbeatInterval: Long,
    )
    
}