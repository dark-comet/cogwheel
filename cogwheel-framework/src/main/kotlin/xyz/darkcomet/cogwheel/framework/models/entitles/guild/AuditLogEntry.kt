package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.AuditLogEntryObject
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEntryId
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEventType
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asAuditLogEntryId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId

data class AuditLogEntry(
    val targetId: String?,
    val changes: List<AuditLogChange>,
    val userId: UserId?,
    val id: AuditLogEntryId,
    val actionType: AuditLogEventType,
    val options: AuditLogOptionalAuditEntryInfo?,
    val reason: String?
) {
    companion object {
        internal fun from(obj: AuditLogEntryObject): AuditLogEntry {
            return obj.toModel()
        }
    }
}

internal fun AuditLogEntryObject.toModel(): AuditLogEntry {
    val actionType = requireNonNull(this, AuditLogEntryObject::actionType).let { AuditLogEventType.fromOrAdd(it) }
    
    return AuditLogEntry(
        targetId = require(this, AuditLogEntryObject::targetId),
        changes = requireNonNullIfPresent(this, AuditLogEntryObject::changes)?.map { it.toModel(actionType) } ?: emptyList(),
        userId = require(this, AuditLogEntryObject::userId)?.asUserId(),
        id = requireNonNull(this, AuditLogEntryObject::id).asAuditLogEntryId(),
        actionType = actionType,
        options = requireNonNullIfPresent(this, AuditLogEntryObject::options)?.let { AuditLogOptionalAuditEntryInfo.from(it) },
        reason = requireNonNullIfPresent(this, AuditLogEntryObject::reason)
    )
}