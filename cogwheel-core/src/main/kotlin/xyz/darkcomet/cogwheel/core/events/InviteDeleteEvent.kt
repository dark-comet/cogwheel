package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class InviteDeleteEvent
internal constructor(override val data: DataObject) : Event<InviteDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        val code: String,
    )

}