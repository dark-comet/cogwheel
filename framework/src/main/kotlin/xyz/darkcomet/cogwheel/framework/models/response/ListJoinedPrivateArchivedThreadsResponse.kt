@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.response

import xyz.darkcomet.cogwheel.core.network.objects.ListJoinedPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ThreadMember
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel

class ListJoinedPrivateArchivedThreadsResponse(
    val threads: List<Channel>,
    val members: List<ThreadMember>,
    val hasMore: Boolean
) {
    companion object {
        internal fun from(obj: ListJoinedPrivateArchivedThreadsResponseObject): ListJoinedPrivateArchivedThreadsResponse {
            return obj.toModel()
        }
    }
}

internal fun ListJoinedPrivateArchivedThreadsResponseObject.toModel(): ListJoinedPrivateArchivedThreadsResponse {
    return ListJoinedPrivateArchivedThreadsResponse(
        threads = this.threads.map { it.toModel() },
        members = this.members.map { it.toModel() },
        hasMore = this.hasMore
    )
}