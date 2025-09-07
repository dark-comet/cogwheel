package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.RoleObject
import xyz.darkcomet.cogwheel.framework.primitives.*
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

@Suppress("unused")
class Role(
    val id: RoleId,
    val name: String,
    @Deprecated("To be removed by Discord API") val color: DiscordColor,
    val colors: RoleColors,
    val hoist: Boolean,
    val icon: String?,
    val unicodeEmoji: String?,
    val position: Int,
    val permissions: BitField<Permission>,
    val managed: Boolean,
    val mentionable: Boolean,
    val tags: RoleTags?,
    val flags: BitField<RoleFlag>
) {
    companion object {
        internal fun from(obj: RoleObject): Role {
            return obj.toModel()
        }
    }
}

internal fun RoleObject.toModel(): Role {
    return Role(
        id = requireNonNull(this, RoleObject::id).asRoleId(),
        name = requireNonNull(this, RoleObject::name),
        color = requireNonNull(this, RoleObject::color).let { DiscordColor.from(it) },
        colors = requireNonNull(this, RoleObject::colors).let { RoleColors.from(it) },
        hoist = requireNonNull(this, RoleObject::hoist),
        icon = this.icon?.value,
        unicodeEmoji = this.unicodeEmoji?.value,
        position = requireNonNull(this, RoleObject::position),
        permissions = requireNonNull(this, RoleObject::permissions).let { BitField.from(it) },
        managed = requireNonNull(this, RoleObject::managed),
        mentionable = requireNonNull(this, RoleObject::mentionable),
        tags = requireNonNullIfPresent(this, RoleObject::tags)?.toModel(),
        flags = requireNonNull(this, RoleObject::flags).let { BitField.from(it) },
    )
}