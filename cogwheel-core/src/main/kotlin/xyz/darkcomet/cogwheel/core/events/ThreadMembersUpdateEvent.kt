package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ThreadMembersUpdateEvent
internal constructor(override val data: DataObject): Event<ThreadMembersUpdateEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        val id: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake,
        @SerialName("member_count") val memberCount: Int,
        @SerialName("added_members") val addedMembers: List<ThreadMemberObject>? = null,
        @SerialName("removed_member_ids") val removedMemberIds: List<Snowflake>? = null
    )
    
}