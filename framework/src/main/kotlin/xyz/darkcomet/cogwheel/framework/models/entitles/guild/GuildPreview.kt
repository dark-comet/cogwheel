package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildPreviewObject
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.Emoji
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.Sticker
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.toModel
import xyz.darkcomet.cogwheel.framework.primitives.GuildFeature
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

@Suppress("unused")
class GuildPreview(
    val id: GuildId,
    val name: String,
    val icon: String?,
    val splash: String?,
    val discoverySplash: String?,
    val emojis: List<Emoji>,
    val features: List<GuildFeature>,
    val approximateMemberCount: Int,
    val approximatePresenceCount: Int,
    val description: String?,
    val stickers: List<Sticker>?,
) {
    companion object {
        internal fun from(obj: GuildPreviewObject): GuildPreview {
            return obj.toModel()
        }
    }
}

internal fun GuildPreviewObject.toModel(): GuildPreview {
    return GuildPreview(
        id = requireNonNull(this, GuildPreviewObject::id).asGuildId(),
        name = requireNonNull(this, GuildPreviewObject::name),
        icon = require(this, GuildPreviewObject::icon),
        splash = require(this, GuildPreviewObject::splash),
        discoverySplash = require(this, GuildPreviewObject::discoverySplash),
        emojis = requireNonNull(this, GuildPreviewObject::emojis).map { it.toModel() },
        features = requireNonNull(this, GuildPreviewObject::features).map { GuildFeature.fromOrAdd(it) },
        approximateMemberCount = requireNonNull(this, GuildPreviewObject::approximateMemberCount),
        approximatePresenceCount = requireNonNull(this, GuildPreviewObject::approximatePresenceCount),
        description = require(this, GuildPreviewObject::description),
        stickers = requireNonNullIfPresent(this, GuildPreviewObject::stickers)?.map { it.toModel()}
    )
}