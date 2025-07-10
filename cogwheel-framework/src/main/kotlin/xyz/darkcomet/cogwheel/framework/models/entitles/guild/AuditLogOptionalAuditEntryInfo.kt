package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.AuditLogOptionalAuditEntryInfoObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

data class AuditLogOptionalAuditEntryInfo(
    val applicationId: ApplicationId? = null,
    val autoModerateRuleName: String? = null,
    val channelId: ChannelId? = null,
    val count: String? = null,
    val deleteMemberDays: String? = null,
    val id: Snowflake? = null,
    val membersRemoved: String? = null,
    val messageId: String? = null,
    val roleName: String? = null,
    val type: String? = null,
    val integrationType: String? = null
) {
    companion object {
        internal fun from(obj: AuditLogOptionalAuditEntryInfoObject): AuditLogOptionalAuditEntryInfo? {
            return obj.toModel()
        }
    }
}

internal fun AuditLogOptionalAuditEntryInfoObject.toModel(): AuditLogOptionalAuditEntryInfo {
    // TODO: Probably want to return a specific, relevant type based on the event type
    // ref: https://discord.com/developers/docs/resources/audit-log#audit-log-entry-object-optional-audit-entry-info
    TODO()
}