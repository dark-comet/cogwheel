package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbedImageObject(
    val url: String,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null
)