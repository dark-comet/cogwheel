package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildSoundboardSoundsUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildSoundboardSoundsUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("soundboard_sounds") val soundboardSounds: List<SoundboardSoundObject>,
        @SerialName("guild_id") val guildId: Snowflake
    )

}