package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildScheduledEventObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    @SerialName("channel_id") val channelId: Snowflake?,
    @SerialName("creator_id") val creatorId: Snowflake? = null,
    val name: String,
    val description: String? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: ISO8601Timestamp? = null,
    @SerialName("privacy_level") val privacyLevel: Int,
    val status: Int,
    @SerialName("entity_type") val entityType: Int,
    @SerialName("entity_id") val entityId: Snowflake?,
    @SerialName("entity_metadata") val entityMetadata: GuildScheduledEventMetadataObject? = null,
    val creator: UserObject? = null,
    @SerialName("user_count") val userCount: Int? = null,
    val image: String? = null,
    @SerialName("recurrence_rule") val recurrenceRule: GuildScheduledEventRecurrenceRuleObject? = null,
)