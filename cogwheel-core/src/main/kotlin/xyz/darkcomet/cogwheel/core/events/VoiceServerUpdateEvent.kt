package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class VoiceServerUpdateEvent
internal constructor(override val data: DataObject) : Event<VoiceServerUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        val token: String,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        val endpoint: String?
    )

}
