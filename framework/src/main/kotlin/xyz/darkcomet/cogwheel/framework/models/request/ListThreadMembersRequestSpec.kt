@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ListThreadMembersRequestSpec(
    internal val channelId: ChannelId,
) {
    internal var withMember: Boolean? = null
    internal var after: UserId? = null
    internal var limit: Int? = null
    
    fun withMember(flag: Boolean): ListThreadMembersRequestSpec
        = apply { this.withMember = flag }
    
    fun after(user: UserId): ListThreadMembersRequestSpec
        = apply { this.after = user }
    
    fun limit(quantity: Int): ListThreadMembersRequestSpec
        = apply { this.limit = quantity }
}