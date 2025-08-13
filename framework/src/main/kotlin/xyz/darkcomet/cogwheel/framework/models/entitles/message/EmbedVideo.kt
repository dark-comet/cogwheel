@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedVideoObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedVideo(
    val url: String?,
    val proxyUrl: String?,
    val height: Int?,
    val width: Int?,
) {
    companion object {
        internal fun from(obj: EmbedVideoObject): EmbedVideo {
            return obj.toModel()
        }
    }
}

internal fun EmbedVideoObject.toModel(): EmbedVideo {
    return EmbedVideo(
        url = requireNonNullIfPresent(this, EmbedVideoObject::url),
        proxyUrl = requireNonNullIfPresent(this, EmbedVideoObject::proxyUrl),
        height = requireNonNullIfPresent(this, EmbedVideoObject::height),
        width = requireNonNullIfPresent(this, EmbedVideoObject::width)
    )
}