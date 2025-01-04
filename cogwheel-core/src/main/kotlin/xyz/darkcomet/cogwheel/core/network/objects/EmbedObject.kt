package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

@Serializable
data class EmbedObject(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = null,
    val timestamp: ISO8601Timestamp? = null,
    val color: Int? = null,
    val footer: EmbedFooterObject? = null,
    val image: EmbedImageObject? = null,
    val thumbnail: EmbedThumbnailObject? = null,
    val video: EmbedVideoObject? = null,
    val provider: EmbedProviderObject? = null,
    val author: EmbedAuthorObject? = null,
    val fields: List<EmbedFieldObject>? = null
)