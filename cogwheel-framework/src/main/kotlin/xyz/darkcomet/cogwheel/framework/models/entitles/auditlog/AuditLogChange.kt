package xyz.darkcomet.cogwheel.framework.models.entitles.auditlog

import xyz.darkcomet.cogwheel.core.network.objects.AuditLogChangeObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEventType

data class AuditLogChange(
    val newValue: Any?,
    val oldValue: Any?,
    val key: Any,
    val channelId: Any?
) {
    companion object {
        internal fun from(obj: AuditLogChangeObject, type: AuditLogEventType): AuditLogChange {
            return obj.toModel(type)
        }
    }
}

internal fun AuditLogChangeObject.toModel(type: AuditLogEventType): AuditLogChange {
    // TODO: Verify the specifics of these exceptions: https://discord.com/developers/docs/resources/audit-log#audit-log-change-object-audit-log-change-exceptions
    return AuditLogChange(
        newValue = this.newValue?.value,
        oldValue = this.oldValue?.value,
        key = requireNonNull(this, AuditLogChangeObject::key),
        channelId = null
    )
}