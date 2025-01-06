package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildBanAddEvent
internal constructor(override val data: DataObject) : Event<GuildBanAddEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val user: UserObject
    )
    
}