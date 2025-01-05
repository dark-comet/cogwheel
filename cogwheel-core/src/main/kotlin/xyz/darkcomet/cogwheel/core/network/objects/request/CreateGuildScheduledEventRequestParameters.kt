package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventRecurrenceRuleObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateGuildScheduledEventRequestParameters(
    val channelId: Snowflake? = null,
    @SerialName("entity_metadata") val entityMetadata: GuildScheduledEventEntityMetadataObject? = null,
    val name: String,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
    @SerialName("scheduled_start_time") val scheduledStartTime: ISO8601Timestamp,
    @SerialName("scheduled_end_time") val scheduledEndTime: ISO8601Timestamp? = null,
    val description: String? = null,
    @SerialName("entity_type") val entityType: Int,
    val image: ImageData? = null,
    @SerialName("recurrence_rule") val recurrenceRule: GuildScheduledEventRecurrenceRuleObject? = null
)