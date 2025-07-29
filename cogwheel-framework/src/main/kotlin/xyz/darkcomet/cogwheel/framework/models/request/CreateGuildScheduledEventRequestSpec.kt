package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

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
    
    // TODO: builder methods
    
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