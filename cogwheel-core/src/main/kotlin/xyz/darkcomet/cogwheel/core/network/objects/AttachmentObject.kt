package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AttachmentObject(
    val id: Snowflake,
    val filename: String? = null,
    val title: String? = null,
    val description: String? = null,
    @SerialName("content_type") val contentType: String? = null,
    val size: Int? = null,
    val url: String? = null,
    @SerialName("proxy_url") val proxyUrl: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val ephemeral: Boolean? = null,
    @SerialName("duration_secs") val durationSecs: Float? = null,
    val waveform: String? = null,
    val flags: Int? = null,
)