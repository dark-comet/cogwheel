@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedFooterObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedFooter(
    val text: String,
    val iconUrl: String?,
    val proxyIconUrl: String?,
) {
    companion object {
        internal fun from(obj: EmbedFooterObject): EmbedFooter {
            return obj.toModel()
        }
    }
}

internal fun EmbedFooterObject.toModel(): EmbedFooter {
    return EmbedFooter(
        text = requireNonNull(this, EmbedFooterObject::text),
        iconUrl = requireNonNullIfPresent(this, EmbedFooterObject::iconUrl),
        proxyIconUrl = requireNonNullIfPresent(this, EmbedFooterObject::proxyIconUrl),
    )
}