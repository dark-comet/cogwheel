package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ThreadListSyncEvent
internal constructor(override val data: DataObject): Event<ThreadListSyncEvent.DataObject> {
    
    @Serializable
    data class DataObject(
        val guildId: Snowflake,
        val channelIds: List<Snowflake>? = null,
        val threads: List<ChannelObject>,
        val members: List<ThreadMemberObject>
    )
    
}