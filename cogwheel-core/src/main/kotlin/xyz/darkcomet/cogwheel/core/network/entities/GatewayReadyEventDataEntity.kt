package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayReadyEventDataEntity(
    val v: Int,
    val user: UserEntity,
    val guilds: List<GuildEntity>,
    @SerialName("session_id") val sessionId: String,
    @SerialName("resume_gateway_url") val resumeGatewayUrl: String,
    val shard: List<Int>? = null,
    val application: ApplicationEntity,
)