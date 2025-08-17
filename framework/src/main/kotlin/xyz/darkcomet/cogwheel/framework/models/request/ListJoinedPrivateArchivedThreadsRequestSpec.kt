package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId

class ListJoinedPrivateArchivedThreadsRequestSpec(
    internal val channelId: ChannelId
) {
    internal var before: ISO8601Timestamp? = null
    internal var limit: Int? = null
    
    fun before(timestamp: ISO8601Timestamp): ListJoinedPrivateArchivedThreadsRequestSpec
        = apply { this.before = timestamp }
    
    fun limit(quantity: Int): ListJoinedPrivateArchivedThreadsRequestSpec
        = apply { this.limit = quantity }
}