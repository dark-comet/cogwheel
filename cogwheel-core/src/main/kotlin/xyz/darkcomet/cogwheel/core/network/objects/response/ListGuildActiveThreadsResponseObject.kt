package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject

@Serializable
data class ListGuildActiveThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>
)