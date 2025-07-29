@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.*

class GuildScheduledEvent(
    val id: GuildScheduledEventId,
    val guildId: GuildId,
    val channelId: ChannelId?,
    val creatorId: UserId?,
    val name: String,
    val description: String?,
    val scheduledStartTime: ISO8601Timestamp,
    val scheduledEndTime: ISO8601Timestamp?,
    val privacyLevel: GuildScheduledEventPrivacyLevel,
    val status: GuildScheduledEventStatus,
    val entityType: GuildScheduledEventEntityType,
    val entityId: Snowflake?,
    val entityMetadata: GuildScheduledEventEntityMetadata?,
    val creator: User?,
    val userCount: Int?,
    val image: DiscordImage?,
    val recurrenceRule: GuildScheduledEventRecurrenceRule?
) {
    companion object {
        internal fun from(obj: GuildScheduledEventObject): GuildScheduledEvent {
            return obj.toModel()
        }
    }
}

internal fun GuildScheduledEventObject.toModel(): GuildScheduledEvent {
    return GuildScheduledEvent(
        id = requireNonNull(this, GuildScheduledEventObject::id).asGuildScheduledEventId(),
        guildId = requireNonNull(this, GuildScheduledEventObject::guildId).asGuildId(),
        channelId = require(this, GuildScheduledEventObject::channelId)?.asChannelId(),
        creatorId = this.creatorId?.value?.asUserId(),
        name = requireNonNull(this, GuildScheduledEventObject::name),
        description = this.description?.value,
        scheduledStartTime = requireNonNull(this, GuildScheduledEventObject::scheduledStartTime),
        scheduledEndTime = require(this, GuildScheduledEventObject::scheduledEndTime),
        privacyLevel = requireNonNull(this, GuildScheduledEventObject::privacyLevel).let { GuildScheduledEventPrivacyLevel.fromOrAdd(it) },
        status = requireNonNull(this, GuildScheduledEventObject::status).let { GuildScheduledEventStatus.fromOrAdd(it) },
        entityType = requireNonNull(this, GuildScheduledEventObject::entityType).let { GuildScheduledEventEntityType.fromOrAdd(it) },
        entityId = require(this, GuildScheduledEventObject::entityId),
        entityMetadata = require(this, GuildScheduledEventObject::entityMetadata)?.toModel(),
        creator = requireNonNullIfPresent(this, GuildScheduledEventObject::creator)?.toModel(),
        userCount = requireNonNullIfPresent(this, GuildScheduledEventObject::userCount),
        image = this.image?.value?.let { DiscordImage.fromDataUriScheme(it) },
        recurrenceRule = require(this, GuildScheduledEventObject::recurrenceRule)?.toModel()
    )
}