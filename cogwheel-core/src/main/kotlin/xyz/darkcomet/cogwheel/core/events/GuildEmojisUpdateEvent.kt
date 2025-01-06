package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildEmojisUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildEmojisUpdateEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val emojis: List<EmojiObject>
    )
    
}