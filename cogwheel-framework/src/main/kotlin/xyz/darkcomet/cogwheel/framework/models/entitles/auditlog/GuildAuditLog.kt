package xyz.darkcomet.cogwheel.framework.models.entitles.auditlog

import xyz.darkcomet.cogwheel.core.network.objects.GuildAuditLogObject
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.AutoModerationRule
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEvent
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.PartialGuildIntegration
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toPartialModel
import xyz.darkcomet.cogwheel.framework.models.entitles.interactions.ApplicationCommand
import xyz.darkcomet.cogwheel.framework.models.entitles.interactions.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.webhook.Webhook
import xyz.darkcomet.cogwheel.framework.models.entitles.webhook.toModel
import xyz.darkcomet.cogwheel.framework.models.requireNonNull

data class GuildAuditLog(
    val applicationCommands: List<ApplicationCommand>,
    val auditLogEntries: List<AuditLogEntry>,
    val autoModerationRules: List<AutoModerationRule>,
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
    return GuildAuditLog(
        applicationCommands = requireNonNull(this, GuildAuditLogObject::applicationCommands).map { it.toModel() },
        auditLogEntries = requireNonNull(this, GuildAuditLogObject::auditLogEntries).map { it.toModel() },
        autoModerationRules = requireNonNull(this, GuildAuditLogObject::autoModerationRules).map { it.toModel() },
        guildScheduledEvents = requireNonNull(this, GuildAuditLogObject::guildScheduledEvents).map { it.toModel() },
        integrations = requireNonNull(this, GuildAuditLogObject::integrations).map { it.toPartialModel() },
        threads = requireNonNull(this, GuildAuditLogObject::threads).map { it.toModel() },
        users = requireNonNull(this, GuildAuditLogObject::users).map { it.toModel() },
        webhooks = requireNonNull(this, GuildAuditLogObject::webhooks).map { it.toModel() }
    )
}