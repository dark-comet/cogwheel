@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.message

import xyz.darkcomet.cogwheel.core.network.objects.EmbedObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.primitives.DiscordColor
import xyz.darkcomet.cogwheel.framework.primitives.EmbedType
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class Embed(
    val title: String?,
    val type: EmbedType?,
    val description: String?,
    val url: String?,
    val timestamp: ISO8601Timestamp?,
    val color: DiscordColor?,
    val footer: EmbedFooter?,
    val image: EmbedImage?,
    val thumbnail: EmbedThumbnail?,
    val video: EmbedVideo?,
    val provider: EmbedProvider?,
    val author: EmbedAuthor?,
    val fields: List<EmbedField>?
) {
    companion object {
        internal fun from(obj: EmbedObject): Embed {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): EmbedObject {
        TODO()
    }
}

internal fun EmbedObject.toModel(): Embed {
    return Embed(
        title = requireNonNullIfPresent(this, EmbedObject::title),
        type = requireNonNullIfPresent(this, EmbedObject::type)?.let { EmbedType.fromOrAdd(it) },
        description = requireNonNullIfPresent(this, EmbedObject::description),
        url = requireNonNullIfPresent(this, EmbedObject::url),
        timestamp = requireNonNullIfPresent(this, EmbedObject::timestamp),
        color = requireNonNullIfPresent(this, EmbedObject::color)?.let { DiscordColor.from(it) },
        footer = requireNonNullIfPresent(this, EmbedObject::footer)?.let { EmbedFooter.from(it) },
        image = requireNonNullIfPresent(this, EmbedObject::image)?.let { EmbedImage.from(it) },
        thumbnail = requireNonNullIfPresent(this, EmbedObject::thumbnail)?.let { EmbedThumbnail.from(it) },
        video = requireNonNullIfPresent(this, EmbedObject::video)?.let { EmbedVideo.from(it) },
        provider = requireNonNullIfPresent(this, EmbedObject::provider)?.let { EmbedProvider.from(it) },
        author = requireNonNullIfPresent(this, EmbedObject::author)?.let { EmbedAuthor.from(it) },
        fields = requireNonNullIfPresent(this, EmbedObject::fields)?.map { it.toModel() }
    )
}