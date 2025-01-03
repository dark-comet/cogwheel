package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayHelloEventDataEntity(
    @SerialName("heartbeat_interval") val heartbeatInterval: Long,
)