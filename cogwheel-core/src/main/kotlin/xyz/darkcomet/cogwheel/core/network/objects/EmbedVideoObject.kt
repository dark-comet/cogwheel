package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbedVideoObject(
    val url: String? = null,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: String? = null,
    val height: String? = null
)