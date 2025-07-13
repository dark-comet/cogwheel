package xyz.darkcomet.cogwheel.framework.models.specs

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationRuleTriggerMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.AutoModerationAction
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.AutoModerationRuleTriggerMetadata
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.function.Consumer

class ModifyGuildAutoModerationRuleRequestSpec(
    internal val guildId: GuildId,
    internal val ruleId: AutoModerationRuleId
) {
    
    internal var name: String? = null
    internal var eventType: Int? = null
    internal var triggerMetadata: AutoModerationRuleTriggerMetadataObject? = null
    internal var actions: List<AutoModerationActionObject>? = null
    internal var enabled: Boolean? = null
    internal var exemptRoles: List<Snowflake>? = null
    internal var exemptChannels: List<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.name = name }

    fun eventType(eventType: AutoModerationEventType): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.eventType = eventType.key }

    fun triggerMetadata(metadata: AutoModerationRuleTriggerMetadata?): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.triggerMetadata = metadata?.toObject() }

    fun triggerMetadata(metadataBuilder: Consumer<AutoModerationRuleTriggerMetadata.BuilderSpec>): ModifyGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.accept(builder)

        this.triggerMetadata = builder.build().toObject()
        return this
    }

    fun triggerMetadata(metadataBuilder: AutoModerationRuleTriggerMetadata.BuilderSpec.() -> Unit): ModifyGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.invoke(builder)

        this.triggerMetadata = builder.build().toObject()
        return this
    }

    fun actions(actions: List<AutoModerationAction>): ModifyGuildAutoModerationRuleRequestSpec
            = apply { this.actions = actions.map { it.toObject() } }

    fun enabled(flag: Boolean): ModifyGuildAutoModerationRuleRequestSpec
            = apply { this.enabled = flag }

    fun exemptRoles(vararg ids: RoleId): ModifyGuildAutoModerationRuleRequestSpec
            = apply { this.exemptRoles = ids.map { it.snowflake }.toList() }

    fun exemptChannels(vararg ids: ChannelId): ModifyGuildAutoModerationRuleRequestSpec
            = apply { this.exemptChannels = ids.map { it.snowflake }.toList() }

    fun auditLogReason(reason: String?): ModifyGuildAutoModerationRuleRequestSpec
            = apply { this.auditLogReason = reason }

    internal fun buildParameters(): ModifyGuildAutoModerationRuleRequestParameters {
        return ModifyGuildAutoModerationRuleRequestParameters(
            name = if (name != null) MaybeAbsent(name) else null,
            eventType = if (eventType != null) MaybeAbsent(eventType) else null,
            triggerMetadata = if (triggerMetadata != null) MaybeAbsent(triggerMetadata) else null,
            actions = if (actions != null) MaybeAbsent(actions) else null,
            enabled = if (enabled != null) MaybeAbsent(enabled) else null,
            exemptRoles = if (exemptRoles != null) MaybeAbsent(exemptRoles) else null,
            exemptChannels = if (exemptChannels != null) MaybeAbsent(exemptChannels) else null,
        )
    }
}