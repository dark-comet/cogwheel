package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ListThreadMembersRequestSpec(
    internal val channelId: ChannelId,
) {
    internal var withMember: Boolean? = null
    internal var after: UserId? = null
    internal var limit: Int? = null
}