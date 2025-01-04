package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject

@Serializable
data class ListPrivateArchivedThreadsResponseObject(
    val threads: List<ChannelObject>,
    val members: List<ThreadMemberObject>,
    @SerialName("has_more") val hasMore: Boolean,
)