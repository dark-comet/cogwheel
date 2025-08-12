@file:Suppress("unused", "CanBeParameter")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ChannelPermissionOverwriteObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelPermissionOverwriteType
import xyz.darkcomet.cogwheel.framework.primitives.Permission

class ChannelPermissionOverwrite(
    val userOrRoleId: Snowflake,
    val type: ChannelPermissionOverwriteType,
    val allow: BitField<Permission>,
    val deny: BitField<Permission>,
) {
    companion object {
        internal fun from(obj: ChannelPermissionOverwriteObject): ChannelPermissionOverwrite {
            return obj.toModel()
        }
    }
}

internal fun ChannelPermissionOverwriteObject.toModel(): ChannelPermissionOverwrite {
    return ChannelPermissionOverwrite(
        userOrRoleId = requireNonNull(this, ChannelPermissionOverwriteObject::id),
        type = requireNonNull(this, ChannelPermissionOverwriteObject::type).let { ChannelPermissionOverwriteType.fromOrAdd(it) },
        allow = requireNonNull(this, ChannelPermissionOverwriteObject::allow).let { BitField.from(it) },
        deny = requireNonNull(this, ChannelPermissionOverwriteObject::deny).let { BitField.from(it) }
    )
}