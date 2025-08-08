package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class ListPrivateArchivedThreadsRequestSpec(
    internal val channelId: ChannelId
) {

    internal var before: ISO8601Timestamp? = null
    internal var limit: Int? = null

}