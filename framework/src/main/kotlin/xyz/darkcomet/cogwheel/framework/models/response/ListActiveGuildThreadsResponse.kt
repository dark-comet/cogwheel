package xyz.darkcomet.cogwheel.framework.models.response

import xyz.darkcomet.cogwheel.core.network.objects.ListActiveGuildThreadsResponseObject
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ThreadMember
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel

class ListActiveGuildThreadsResponse(
    val threads: List<Channel>, // TODO: Consider using a specific thread-channel type when expanding the domain
    val members: List<ThreadMember>,
) {
    companion object {
        internal fun from(obj: ListActiveGuildThreadsResponseObject): ListActiveGuildThreadsResponse {
            return obj.toModel()
        }
    }
}

internal fun ListActiveGuildThreadsResponseObject.toModel(): ListActiveGuildThreadsResponse {
    return ListActiveGuildThreadsResponse(
        threads = this.threads.map { it.toModel() },
        members = this.members.map { it.toModel() }
    )
}