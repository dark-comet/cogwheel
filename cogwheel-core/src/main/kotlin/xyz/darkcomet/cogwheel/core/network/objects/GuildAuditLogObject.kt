package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildAuditLogObject(
    @SerialName("application_commands") val applicationCommands: List<ApplicationCommandObject>,
    @SerialName("audit_log_entries") val auditLogEntries: List<AuditLogEntryObject>,
    @SerialName("autoModerationRules") val autoModerationRules: List<GuildAutoModerationRuleObject>,
    @SerialName("guild_scheduled_events") val guildScheduledEvents: List<GuildScheduledEventObject>,
    val integrations: List<GuildIntegrationObject>,
    val threads: List<ChannelObject>,
    val users: List<UserObject>,
    val webhooks: List<WebhookObject>
)