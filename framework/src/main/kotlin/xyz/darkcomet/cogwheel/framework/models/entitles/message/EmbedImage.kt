@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedImageObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedImage(
    val url: String?,
    val proxyUrl: String?,
    val height: Int?,
    val width: Int?,
) {
    companion object {
        internal fun from(obj: EmbedImageObject): EmbedImage {
            return obj.toModel()
        }
    }
}

internal fun EmbedImageObject.toModel(): EmbedImage {
    return EmbedImage(
        url = requireNonNullIfPresent(this, EmbedImageObject::url),
        proxyUrl = requireNonNullIfPresent(this, EmbedImageObject::proxyUrl),
        height = requireNonNullIfPresent(this, EmbedImageObject::height),
        width = requireNonNullIfPresent(this, EmbedImageObject::width)
    )
}