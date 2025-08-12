@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.EditChannelPermissionsParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.ChannelPermissionOverwriteType
import xyz.darkcomet.cogwheel.framework.primitives.Permission

class EditChannelPermissionsSpec(
    internal val channelId: ChannelId,
    internal val overwriteId: Snowflake
) {
    private var allow: MaybeAbsent<String?>? = null
    private var deny: MaybeAbsent<String?>? = null
    private var type: Int? = null
    
    internal var auditLogReason: String? = null 
    
    fun allow(vararg permission: Permission): EditChannelPermissionsSpec
        = apply { this.allow = MaybeAbsent(BitField.from(permission).toString()) }
    
    fun allow(permission: BitField<Permission>): EditChannelPermissionsSpec
        = apply { this.allow = MaybeAbsent(permission.toString()) }
    
    fun deny(vararg permission: Permission): EditChannelPermissionsSpec
        = apply { this.deny = MaybeAbsent(BitField.from(permission).toString()) }
    
    fun deny(permission: BitField<Permission>): EditChannelPermissionsSpec
        = apply { this.deny = MaybeAbsent(permission.toString()) }
    
    fun type(type: ChannelPermissionOverwriteType): EditChannelPermissionsSpec
        = apply { this.type = type.key }
    
    fun auditLogReason(reason: String?): EditChannelPermissionsSpec 
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): EditChannelPermissionsParameters {
        return EditChannelPermissionsParameters(
            allow = this.allow,
            deny = this.deny,
            type = this.type ?: throw InvalidModelException("'type' is required")
        )
    }
}