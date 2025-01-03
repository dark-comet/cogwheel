package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayResumeDataEntity(
    val token: String,
    @SerialName("session_id") val sessionId: String,
    val seq: Int
)