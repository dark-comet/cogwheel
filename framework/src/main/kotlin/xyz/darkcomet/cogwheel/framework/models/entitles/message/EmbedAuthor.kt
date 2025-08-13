@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedAuthorObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull

class EmbedAuthor(
    val name: String,
    val url: String?,
    val iconUrl: String?,
    val proxyIconUrl: String?
) {
    companion object {
        internal fun from(obj: EmbedAuthorObject): EmbedAuthor {
            return obj.toModel()
        }
    }
}

internal fun EmbedAuthorObject.toModel(): EmbedAuthor {
    return EmbedAuthor(
        name = requireNonNull(this, EmbedAuthorObject::name),
        url = requireNonNull(this, EmbedAuthorObject::url),
        iconUrl = requireNonNull(this, EmbedAuthorObject::iconUrl),
        proxyIconUrl = requireNonNull(this, EmbedAuthorObject::proxyIconUrl),
    )
}