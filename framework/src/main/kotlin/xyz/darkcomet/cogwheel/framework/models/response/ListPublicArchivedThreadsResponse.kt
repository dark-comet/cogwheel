@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.response

import xyz.darkcomet.cogwheel.core.network.objects.ListPublicArchivedThreadsResponseObject
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ThreadMember
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel

class ListPublicArchivedThreadsResponse(
    val threads: List<Channel>,
    val members: List<ThreadMember>,
    val hasMore: Boolean
) {
    companion object {
        internal fun from(obj: ListPublicArchivedThreadsResponseObject): ListPublicArchivedThreadsResponse {
            return obj.toModel()
        }
    }
}

internal fun ListPublicArchivedThreadsResponseObject.toModel(): ListPublicArchivedThreadsResponse {
    return ListPublicArchivedThreadsResponse(
        threads = this.threads.map { it.toModel() },
        members = this.members.map { it.toModel() },
        hasMore = this.hasMore
    )
}