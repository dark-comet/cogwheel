package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildScheduledEventUserRemoveEvent
internal constructor(override val data: DataObject) : Event<GuildScheduledEventUserRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_scheduled_event_id") val guildScheduledEventId: Snowflake,
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
    )

}