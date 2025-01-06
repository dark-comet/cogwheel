package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ChannelPinsUpdateEvent
internal constructor(override val data: DataObject) : Event<ChannelPinsUpdateEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("last_pin_timestamp") val lastPinTimestamp: ISO8601Timestamp? = null,
    )
    
}