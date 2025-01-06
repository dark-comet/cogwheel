package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.RoleObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildRoleCreateEvent
internal constructor(override val data: DataObject) : Event<GuildRoleCreateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val role: RoleObject
    )

}