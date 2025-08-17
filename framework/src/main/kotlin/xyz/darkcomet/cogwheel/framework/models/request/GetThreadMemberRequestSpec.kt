@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GetThreadMemberRequestSpec(
    internal val channelId: ChannelId,
    internal val userId: UserId
) {
    internal var withMember: Boolean? = null
    
    fun withMember(flag: Boolean): GetThreadMemberRequestSpec
        = apply { withMember = flag }
}