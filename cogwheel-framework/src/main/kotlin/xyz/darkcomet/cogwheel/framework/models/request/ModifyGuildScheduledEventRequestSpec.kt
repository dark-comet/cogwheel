@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEventEntityMetadata
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEventRecurrenceRule
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventEntityType
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventId
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventPrivacyLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventStatus
import java.util.function.Consumer

class ModifyGuildScheduledEventRequestSpec(
    internal val guildId: GuildId,
    internal val eventId: GuildScheduledEventId
) {
    internal var channelId: MaybeAbsent<Snowflake?>? = null
    internal var entityMetadata: MaybeAbsent<GuildScheduledEventEntityMetadataObject?>? = null
    internal var name: MaybeAbsent<String>? = null
    internal var privacyLevel: MaybeAbsent<Int>? = null
    internal var scheduledStartTime: MaybeAbsent<ISO8601Timestamp>? = null
    internal var scheduledEndTime: MaybeAbsent<ISO8601Timestamp>? = null
    internal var description: MaybeAbsent<String?>? = null
    internal var entityType: MaybeAbsent<Int>? = null
    internal var status: MaybeAbsent<Int>? = null
    internal var image: MaybeAbsent<String>? = null
    internal var recurrenceRule: MaybeAbsent<GuildScheduledEventRecurrenceRuleObject?>? = null
    
    internal var auditLogReason: String? = null
    
    fun channelId(id: ChannelId?): ModifyGuildScheduledEventRequestSpec
        = apply { this.channelId = MaybeAbsent(id?.snowflake) }
    
    fun entityMetadata(metadata: GuildScheduledEventEntityMetadata?): ModifyGuildScheduledEventRequestSpec
        = apply { this.entityMetadata = MaybeAbsent(metadata?.toObject()) }
    
    // TODO: entityMetadata() - lambda with receiver
    // TODO: entityMetadata() - Java Consumer<T>
    
    fun name(name: String): ModifyGuildScheduledEventRequestSpec
        = apply { this.name = MaybeAbsent(name) }
    
    fun privacyLevel(level: GuildScheduledEventPrivacyLevel): ModifyGuildScheduledEventRequestSpec
        = apply { this.privacyLevel = MaybeAbsent(level.key) }
    
    fun scheduledStartTime(time: ISO8601Timestamp): ModifyGuildScheduledEventRequestSpec
        = apply { this.scheduledStartTime = MaybeAbsent(time) }
    
    fun scheduledEndTime(time: ISO8601Timestamp): ModifyGuildScheduledEventRequestSpec
        = apply { this.scheduledEndTime = MaybeAbsent(time) }
    
    fun description(description: String?): ModifyGuildScheduledEventRequestSpec
        = apply { this.description = MaybeAbsent(description) }
    
    fun entityType(type: GuildScheduledEventEntityType): ModifyGuildScheduledEventRequestSpec
        = apply { this.entityType = MaybeAbsent(type.key) }
    
    fun status(status: GuildScheduledEventStatus): ModifyGuildScheduledEventRequestSpec
        = apply { this.status = MaybeAbsent(status.key) }
    
    fun image(image: DiscordImage?): ModifyGuildScheduledEventRequestSpec
        = apply { this.image = MaybeAbsent(image?.data.toString()) }
    
    fun recurrenceRule(rule: GuildScheduledEventRecurrenceRule?): ModifyGuildScheduledEventRequestSpec
        = apply { this.recurrenceRule = MaybeAbsent(rule?.toObject()) }
    
    // TODO
//    fun recurrenceRule(ruleBuilder: GuildScheduledEventRecurrenceRule.BuilderSpec.() -> Unit): ModifyGuildScheduledEventRequestSpec {
//        
//    }
//    
//    fun recurrenceRule(ruleBuilder: Consumer<GuildScheduledEventRecurrenceRule.BuilderSpec>): ModifyGuildScheduledEventRequestSpec {
//        
//    }
    
    fun auditLogReason(reason: String): ModifyGuildScheduledEventRequestSpec
        = apply { this.auditLogReason = reason }
    
    internal fun buildParameters(): ModifyGuildScheduledEventRequestParameters {
        return ModifyGuildScheduledEventRequestParameters(
            channelId = this.channelId,
            entityMetadata = this.entityMetadata,
            name = this.name,
            privacyLevel = this.privacyLevel,
            scheduledStartTime = this.scheduledStartTime,
            scheduledEndTime = this.scheduledEndTime,
            description = this.description,
            entityType = this.entityType,
            status = this.status,
            image = this.image,
            recurrenceRule = this.recurrenceRule
        )
    }
}