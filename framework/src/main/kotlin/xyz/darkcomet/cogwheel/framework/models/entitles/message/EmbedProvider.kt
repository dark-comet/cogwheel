@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedProviderObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedProvider(
    val name: String?,
    val url: String?
) {
    companion object {
        internal fun from(obj: EmbedProviderObject): EmbedProvider {
            return obj.toModel()
        }
    }
}

internal fun EmbedProviderObject.toModel(): EmbedProvider {
    return EmbedProvider(
        name = requireNonNullIfPresent(this, EmbedProviderObject::name),
        url = requireNonNullIfPresent(this, EmbedProviderObject::url),
    )
}