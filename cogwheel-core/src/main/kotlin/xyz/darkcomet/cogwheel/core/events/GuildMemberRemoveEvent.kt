package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildMemberRemoveEvent
internal constructor(override val data: DataObject) : Event<GuildMemberRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val user: UserObject
    )

}