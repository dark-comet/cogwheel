package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayIdentifyEventDataObject(
    val token: String,
    val properties: IdentifyConnectionPropertiesObject,
    val compress: Boolean? = null,
    @SerialName("large_threshold") val largeThreshold: Int? = null,
    val shard: List<Int>? = null,
    val presence: UpdatePresenceObject? = null,
    val intents: Int
) {
    @Serializable
    data class IdentifyConnectionPropertiesObject(
        val os: String,
        val browser: String,
        val device: String
    )
}