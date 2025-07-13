package xyz.darkcomet.cogwheel.framework.models.specs

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationRuleTriggerMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.AutoModerationAction
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.AutoModerationRuleTriggerMetadata
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.function.Consumer

class CreateGuildAutoModerationRuleRequestSpec(internal val guildId: GuildId) {
    
    internal var name: String? = null
    internal var eventType: Int? = null
    internal var triggerType: Int? = null
    internal var triggerMetadata: AutoModerationRuleTriggerMetadataObject? = null
    internal var actions: List<AutoModerationActionObject> = ArrayList(2)
    internal var enabled: Boolean? = null
    internal var exemptRoles: List<Snowflake>? = null
    internal var exemptChannels: List<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): CreateGuildAutoModerationRuleRequestSpec 
        = apply { this.name = name }
    
    fun eventType(eventType: AutoModerationEventType): CreateGuildAutoModerationRuleRequestSpec 
        = apply { this.eventType = eventType.key }
    
    fun triggerType(triggerType: AutoModerationTriggerType): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.triggerType = triggerType.key }
    
    fun triggerMetadata(metadata: AutoModerationRuleTriggerMetadata?): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.triggerMetadata = metadata?.toObject() }
    
    fun triggerMetadata(metadataBuilder: Consumer<AutoModerationRuleTriggerMetadata.BuilderSpec>): CreateGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.accept(builder)
        
        this.triggerMetadata = builder.build().toObject()
        return this
    }
    
    fun triggerMetadata(metadataBuilder: AutoModerationRuleTriggerMetadata.BuilderSpec.() -> Unit): CreateGuildAutoModerationRuleRequestSpec {
        val builder = AutoModerationRuleTriggerMetadata.builder()
        metadataBuilder.invoke(builder)
        
        this.triggerMetadata = builder.build().toObject()
        return this 
    }
    
    fun actions(actions: List<AutoModerationAction>): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.actions = actions.map { it.toObject() } }
    
    fun actions(vararg actions: AutoModerationAction): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.actions = actions.map { it.toObject() }.toList() }
    
    fun enabled(flag: Boolean): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.enabled = flag }
    
    fun exemptRoles(vararg ids: RoleId): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.exemptRoles = ids.map { it.snowflake }.toList() }
    
    fun exemptChannels(vararg ids: ChannelId): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.exemptChannels = ids.map { it.snowflake }.toList() }
    
    fun auditLogReason(reason: String?): CreateGuildAutoModerationRuleRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): CreateGuildAutoModerationRuleRequestParameters {
        val finalName = this.name
        val finalEventType = this.eventType
        val finalTriggerType = this.triggerType
        
        if (finalName == null) {
            throw InvalidModelException("'name' is required")
        }
        if (finalEventType == null) {
            throw InvalidModelException("'eventType' is required")
        }
        if (finalTriggerType == null) {
            throw InvalidModelException("'triggerType' is required")
        }
        
        return CreateGuildAutoModerationRuleRequestParameters(
            name = finalName, 
            eventType = finalEventType,
            triggerType = finalTriggerType,
            triggerMetadata = if (triggerMetadata != null) MaybeAbsent(triggerMetadata) else null,
            actions = actions,
            enabled = if (enabled != null) MaybeAbsent(enabled) else null,
            exemptRoles = if (exemptRoles != null) MaybeAbsent(exemptRoles) else null,
            exemptChannels = if (exemptChannels != null) MaybeAbsent(exemptChannels) else null,
        )
    }
}