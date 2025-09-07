package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.RoleColorsObject
import xyz.darkcomet.cogwheel.framework.primitives.DiscordColor
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull

@Suppress("unused")
class RoleColors(
    val primaryColor: DiscordColor,
    val secondaryColor: DiscordColor?,
    val tertiaryColor: DiscordColor?,
) {
    companion object {
        internal fun from(obj: RoleColorsObject): RoleColors {
            return obj.toModel()
        }
    }
}

internal fun RoleColorsObject.toModel(): RoleColors {
    return RoleColors(
        primaryColor = requireNonNull(this, RoleColorsObject::primaryColor).let { DiscordColor.from(it) },
        secondaryColor = require(this, RoleColorsObject::secondaryColor)?.let { DiscordColor.from(it) },
        tertiaryColor = require(this, RoleColorsObject::tertiaryColor)?.let { DiscordColor.from(it) }
    )
}