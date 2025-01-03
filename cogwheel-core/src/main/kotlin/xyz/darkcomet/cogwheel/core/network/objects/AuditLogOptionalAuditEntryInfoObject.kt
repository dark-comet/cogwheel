package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AuditLogOptionalAuditEntryInfoObject(
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("auto_moderation_rule_name") val autoModerationRuleName: String? = null,
    @SerialName("auto_moderation_rule_trigger_type") val autoModerationRuleTriggerType: String? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val count: String? = null,
    @SerialName("delete_member_days") val deleteMemberDays: String? = null,
    val id: Snowflake? = null,
    @SerialName("members_removed") val membersRemoved: String? = null,
    @SerialName("message_id") val messageId: Snowflake? = null,
    @SerialName("role_name") val roleName: String? = null,
    val type: String? = null,
    @SerialName("integration_type") val integrationType: String? = null,
)