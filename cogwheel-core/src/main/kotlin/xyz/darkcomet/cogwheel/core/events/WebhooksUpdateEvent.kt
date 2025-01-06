package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class WebhooksUpdateEvent
internal constructor(override val data: DataObject) : Event<WebhooksUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake? = null,
        @SerialName("channel_id") val channelId: Snowflake,
    )

}