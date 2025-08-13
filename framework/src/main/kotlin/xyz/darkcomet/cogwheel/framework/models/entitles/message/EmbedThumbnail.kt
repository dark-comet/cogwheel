@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedThumbnailObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedThumbnail(
    val url: String,
    val proxyUrl: String?,
    val height: Int?,
    val width: Int?
) {
    companion object {
        internal fun from(obj: EmbedThumbnailObject): EmbedThumbnail {
            return obj.toModel()
        }
    }
}

internal fun EmbedThumbnailObject.toModel(): EmbedThumbnail {
    return EmbedThumbnail(
        url = requireNonNull(this, EmbedThumbnailObject::url),
        proxyUrl = requireNonNullIfPresent(this, EmbedThumbnailObject::proxyUrl),
        height = requireNonNullIfPresent(this, EmbedThumbnailObject::height),
        width = requireNonNullIfPresent(this, EmbedThumbnailObject::width)
    )
}