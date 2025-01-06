package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.StickerObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildStickersUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildStickersUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val stickers: List<StickerObject>
    )

}