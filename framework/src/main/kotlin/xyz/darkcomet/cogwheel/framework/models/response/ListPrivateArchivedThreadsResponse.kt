@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.response

import xyz.darkcomet.cogwheel.core.network.objects.ListPrivateArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.ListPublicArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ThreadMember
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel

class ListPrivateArchivedThreadsResponse(
    val threads: List<Channel>,
    val members: List<ThreadMember>,
    val hasMore: Boolean
) {
    companion object {
        internal fun from(obj: ListPrivateArchivedThreadsResponseObject): ListPrivateArchivedThreadsResponse {
            return obj.toModel()
        }
    }
}

internal fun ListPrivateArchivedThreadsResponseObject.toModel(): ListPrivateArchivedThreadsResponse {
    return ListPrivateArchivedThreadsResponse(
        threads = this.threads.map { it.toModel() },
        members = this.members.map { it.toModel() },
        hasMore = this.hasMore
    )
}