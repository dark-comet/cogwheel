package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayReadyEventDataObject(
    val v: Int,
    val user: UserObject,
    val guilds: List<GuildObject>,
    @SerialName("session_id") val sessionId: String,
    @SerialName("resume_gateway_url") val resumeGatewayUrl: String,
    val shard: List<Int>? = null,
    val application: ApplicationObject,
)