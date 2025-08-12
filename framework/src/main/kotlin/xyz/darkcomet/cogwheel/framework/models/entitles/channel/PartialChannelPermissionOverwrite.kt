@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ChannelPermissionOverwriteObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelPermissionOverwriteType
import xyz.darkcomet.cogwheel.framework.primitives.Permission
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class PartialChannelPermissionOverwrite(
    val userOrRoleId: Snowflake,
    val type: ChannelPermissionOverwriteType,
    val allow: MaybeAbsent<BitField<Permission>?>?,
    val deny: MaybeAbsent<BitField<Permission>?>?,
) {
    companion object {
        internal fun from(obj: ChannelPermissionOverwriteObject): PartialChannelPermissionOverwrite {
            return obj.toPartialModel()
        }
    }
    
    internal fun toObject(): ChannelPermissionOverwriteObject {
        val allowValue = if (allow == null) null else MaybeAbsent(allow.let { it.value?.toString() })
        val denyValue = if (deny == null) null else MaybeAbsent(deny.let { it.value?.toString() })
        
        return ChannelPermissionOverwriteObject(
            id = MaybeAbsent(userOrRoleId),
            type = MaybeAbsent(type.key),
            allow = allowValue,
            deny = denyValue
        )
    }
}

internal fun ChannelPermissionOverwriteObject.toPartialModel(): PartialChannelPermissionOverwrite {
    val allow = requireNonNullIfPresent(this, ChannelPermissionOverwriteObject::allow)?.let { BitField.from<Permission>(it) }
    val deny = requireNonNullIfPresent(this, ChannelPermissionOverwriteObject::deny)?.let { BitField.from<Permission>(it) }
    
    return PartialChannelPermissionOverwrite(
        userOrRoleId = requireNonNull(this, ChannelPermissionOverwriteObject::id),
        type = requireNonNull(this, ChannelPermissionOverwriteObject::type).let { ChannelPermissionOverwriteType.fromOrAdd(it) },
        allow = if (allow != null) MaybeAbsent(allow) else null,
        deny = if (deny != null) MaybeAbsent(deny) else null
    )
}