@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

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
    
    internal var name: MaybeAbsent<String>? = null
    internal var eventType: MaybeAbsent<Int>? = null
    internal var triggerMetadata: MaybeAbsent<AutoModerationRuleTriggerMetadataObject>? = null
    internal var actions: MaybeAbsent<List<AutoModerationActionObject>>? = null
    internal var enabled: MaybeAbsent<Boolean>? = null
    internal var exemptRoles: MaybeAbsent<List<Snowflake>>? = null
    internal var exemptChannels: MaybeAbsent<List<Snowflake>>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.name = MaybeAbsent(name) }

    fun eventType(eventType: AutoModerationEventType): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.eventType = MaybeAbsent(eventType.key) }

    fun triggerMetadata(metadata: AutoModerationRuleTriggerMetadata?): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.triggerMetadata = MaybeAbsent(metadata?.toObject()) }

    fun triggerMetadata(metadataBuilder: Consumer<AutoModerationRuleTriggerMetadata.BuilderSpec>): ModifyGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.accept(builder)

        this.triggerMetadata = MaybeAbsent(builder.build().toObject())
        return this
    }

    fun triggerMetadata(metadataBuilder: AutoModerationRuleTriggerMetadata.BuilderSpec.() -> Unit): ModifyGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.invoke(builder)

        this.triggerMetadata = MaybeAbsent(builder.build().toObject())
        return this
    }

    fun actions(actions: List<AutoModerationAction>): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.actions = MaybeAbsent(actions.map { it.toObject() }) }

    fun actions(vararg actions: AutoModerationAction): ModifyGuildAutoModerationRuleRequestSpec 
        = actions(actions.toList())
    
    fun enabled(flag: Boolean): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.enabled = MaybeAbsent(flag) }

    fun exemptRoles(vararg ids: RoleId): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.exemptRoles = MaybeAbsent(ids.map { it.snowflake }.toList()) }

    fun exemptChannels(vararg ids: ChannelId): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.exemptChannels = MaybeAbsent(ids.map { it.snowflake }.toList()) }

    fun auditLogReason(reason: String?): ModifyGuildAutoModerationRuleRequestSpec
        = apply { this.auditLogReason = reason }

    internal fun buildParameters(): ModifyGuildAutoModerationRuleRequestParameters {
        return ModifyGuildAutoModerationRuleRequestParameters(
            name = name,
            eventType = eventType,
            triggerMetadata = triggerMetadata,
            actions = actions,
            enabled = enabled,
            exemptRoles = exemptRoles,
            exemptChannels = exemptChannels,
        )
    }
}