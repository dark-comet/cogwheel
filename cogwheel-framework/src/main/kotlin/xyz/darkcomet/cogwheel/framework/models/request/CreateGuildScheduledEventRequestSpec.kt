@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEventEntityMetadata
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEventRecurrenceRule
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.function.Consumer

class CreateGuildScheduledEventRequestSpec(internal val guildId: GuildId) {
    
    internal var channelId: MaybeAbsent<Snowflake>? = null
    internal var entityMetadata: MaybeAbsent<GuildScheduledEventEntityMetadataObject>? = null
    internal var name: String? = null
    internal var privacyLevel: Int? = null
    internal var scheduledStartTime: ISO8601Timestamp? = null
    internal var scheduledEndTime: MaybeAbsent<ISO8601Timestamp>? = null
    internal var description: MaybeAbsent<String>? = null
    internal var entityType: Int? = null
    internal var image: MaybeAbsent<String>? = null
    internal var recurrenceRule: MaybeAbsent<GuildScheduledEventRecurrenceRuleObject>? = null
    
    internal var auditLogReason: String? = null
    
    fun channelId(id: ChannelId): CreateGuildScheduledEventRequestSpec 
        = apply { this.channelId = MaybeAbsent(id.snowflake) }
    
    fun entityMetadata(metadata: GuildScheduledEventEntityMetadata): CreateGuildScheduledEventRequestSpec
        = apply { this.entityMetadata = MaybeAbsent(metadata.toObject()) }
    
    fun entityMetadata(metadataBuilder: GuildScheduledEventEntityMetadata.BuilderSpec.() -> Unit): CreateGuildScheduledEventRequestSpec {
        val builder = GuildScheduledEventEntityMetadata.BuilderSpec()
        metadataBuilder.invoke(builder)

        val model = builder.build()
        this.entityMetadata = MaybeAbsent(model.toObject())

        return this
    }
    
    fun entityMetadata(metadataBuilder: Consumer<GuildScheduledEventEntityMetadata.BuilderSpec>): CreateGuildScheduledEventRequestSpec {
        val builder = GuildScheduledEventEntityMetadata.BuilderSpec()
        metadataBuilder.accept(builder)

        val model = builder.build()
        this.entityMetadata = MaybeAbsent(model.toObject())

        return this
    }
    
    fun name(name: String): CreateGuildScheduledEventRequestSpec
        = apply { this.name = name }
    
    fun privacyLevel(level: GuildScheduledEventPrivacyLevel): CreateGuildScheduledEventRequestSpec
        = apply { this.privacyLevel = level.key }
    
    fun scheduledStartTime(time: ISO8601Timestamp): CreateGuildScheduledEventRequestSpec
        = apply { this.scheduledStartTime = time }
    
    fun scheduledEndTime(time: ISO8601Timestamp?): CreateGuildScheduledEventRequestSpec
        = apply { this.scheduledEndTime = if(time != null) MaybeAbsent(time) else null }
    
    fun description(description: String): CreateGuildScheduledEventRequestSpec
        = apply { this.description = MaybeAbsent(description) }
    
    fun entityType(type: GuildScheduledEventEntityType): CreateGuildScheduledEventRequestSpec
        = apply { this.entityType = type.key }
    
    fun image(image: DiscordImage?): CreateGuildScheduledEventRequestSpec
        = apply { this.image = if (image != null) MaybeAbsent(image.data.toString()) else null }
    
    fun recurrenceRule(rule: GuildScheduledEventRecurrenceRule?): CreateGuildScheduledEventRequestSpec
        = apply { this.recurrenceRule = if (rule != null) MaybeAbsent(rule.toObject()) else null }
    
    fun recurrenceRule(ruleBuilder: GuildScheduledEventRecurrenceRule.BuilderSpec.() -> Unit): CreateGuildScheduledEventRequestSpec {
        val builder = GuildScheduledEventRecurrenceRule.BuilderSpec()
        ruleBuilder.invoke(builder)
        
        val model = builder.build()
        this.recurrenceRule = MaybeAbsent(model.toObject())
        
        return this
    }
    
    fun recurrenceRule(ruleBuilder: Consumer<GuildScheduledEventRecurrenceRule.BuilderSpec>): CreateGuildScheduledEventRequestSpec {
        val builder = GuildScheduledEventRecurrenceRule.BuilderSpec()
        ruleBuilder.accept(builder)
        
        val model = builder.build()
        this.recurrenceRule = MaybeAbsent(model.toObject())
        
        return this
    }
    
    internal fun buildParameters(): CreateGuildScheduledEventRequestParameters {
        return CreateGuildScheduledEventRequestParameters(
            channelId = this.channelId,
            entityMetadata = this.entityMetadata,
            name = this.name ?: throw InvalidModelException("'name' is required"),
            privacyLevel = this.privacyLevel ?: throw InvalidModelException("'privacyLevel' is required"),
            scheduledStartTime = this.scheduledStartTime ?: throw InvalidModelException("'scheduledStartTime' is required"),
            scheduledEndTime = this.scheduledEndTime,
            description = this.description ?: throw InvalidModelException("'description' is required"),
            entityType = this.entityType ?: throw InvalidModelException("'entityType' is required"),
            image = this.image,
            recurrenceRule = this.recurrenceRule
        )
    }
    
}