package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageDeleteBulkEvent
internal constructor(override val data: DataObject) : Event<MessageDeleteBulkEvent.DataObject> {

    @Serializable
    data class DataObject(
        val ids: List<Snowflake>,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null
    )

}
