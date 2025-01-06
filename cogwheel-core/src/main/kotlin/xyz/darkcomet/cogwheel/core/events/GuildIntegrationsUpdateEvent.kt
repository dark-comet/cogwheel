package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildIntegrationsUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildIntegrationsUpdateEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake
    )
    
}