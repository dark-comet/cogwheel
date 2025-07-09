package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildAuditLogObject
import xyz.darkcomet.cogwheel.framework.models.User
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.interactions.ApplicationCommand
import xyz.darkcomet.cogwheel.framework.models.entitles.webhook.Webhook

data class GuildAuditLog(
    val applicationCommands: List<ApplicationCommand>,
    val auditLogEntries: List<GuildAuditLogEntry>,
    val autoModerationRules: List<GuildAutoModerationRule>,
    val guildScheduledEvents: List<GuildScheduledEvent>,
    val integrations: List<PartialGuildIntegration>,
    val threads: List<Channel>,
    val users: List<User>,
    val webhooks: List<Webhook>
) {
    companion object {
        internal fun from(obj: GuildAuditLogObject): GuildAuditLog {
            return obj.toModel()
        } 
    }
}

internal fun GuildAuditLogObject.toModel(): GuildAuditLog {
    TODO()
//    return GuildAuditLog(
//        applicationCommands = requireNonNull(this, GuildAuditLogObject::applicationCommands).map { it.toModel() },
//        auditLogEntries = requireNonNull(this, GuildAuditLogObject::auditLogEntries).map { it.toModel() },
//        autoModerationRules = requireNonNull(this, GuildAuditLogObject::autoModerationRules).map { it.toModel() },
//        guildScheduledEvents = requireNonNull(this, GuildAuditLogObject::guildScheduledEvents).map { it.toModel() },
//        integrations = requireNonNull(this, GuildAuditLogObject::integrations).map { it.toModel() },
//        threads = requireNonNull(this, GuildAuditLogObject::threads).map { it.toModel() },
//        users = requireNonNull(this, GuildAuditLogObject::users).map { it.toModel() },
//        webhooks = requireNonNull(this, GuildAuditLogObject::webhooks).map { it.toModel() }
//    )
}