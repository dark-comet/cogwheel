package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.core.network.objects.UserObject

class GatewayReadyEvent 
internal constructor(override val data: DataObject) : Event<GatewayReadyEvent.DataObject> {

    @Serializable
    data class DataObject(
        val v: Int,
        val user: UserObject,
        val guilds: List<GuildObject>,
        @SerialName("session_id") val sessionId: String,
        @SerialName("resume_gateway_url") val resumeGatewayUrl: String,
        val shard: List<Int>? = null,
        val application: ApplicationObject,
    )
    
}