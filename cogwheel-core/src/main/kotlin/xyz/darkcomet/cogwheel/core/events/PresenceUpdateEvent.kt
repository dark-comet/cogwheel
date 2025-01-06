package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ActivityObject
import xyz.darkcomet.cogwheel.core.network.objects.ClientStatusObject
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class PresenceUpdateEvent
internal constructor(override val data: DataObject) : Event<PresenceUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        val user: UserObject,
        @SerialName("guild_id") val guildId: Snowflake,
        val status: String,
        val activities: List<ActivityObject>,
        @SerialName("client_status") val clientStatus: ClientStatusObject
    )

}
