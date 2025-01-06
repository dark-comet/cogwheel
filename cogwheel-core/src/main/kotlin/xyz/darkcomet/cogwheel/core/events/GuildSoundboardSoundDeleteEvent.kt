package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildSoundboardSoundDeleteEvent
internal constructor(override val data: DataObject) : Event<GuildSoundboardSoundDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("sound_id") val soundId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake
    )
    
}