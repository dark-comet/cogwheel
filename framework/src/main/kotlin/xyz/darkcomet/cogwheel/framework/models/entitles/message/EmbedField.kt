@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedFieldObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class EmbedField(
    val name: String,
    val value: String,
    val inline: Boolean?
) {
    companion object {
        internal fun from(obj: EmbedFieldObject): EmbedField {
            return obj.toModel()
        }
    }
}

internal fun EmbedFieldObject.toModel(): EmbedField {
    return EmbedField(
        name = requireNonNull(this, EmbedFieldObject::name),
        value = requireNonNull(this, EmbedFieldObject::value),
        inline = requireNonNullIfPresent(this, EmbedFieldObject::inline)
    )
}