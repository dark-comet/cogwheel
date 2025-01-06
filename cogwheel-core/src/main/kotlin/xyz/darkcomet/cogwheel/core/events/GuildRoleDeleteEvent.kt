package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildRoleDeleteEvent
internal constructor(override val data: DataObject) : Event<GuildRoleDeleteEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("role_id") val roleId: Snowflake
    )

}