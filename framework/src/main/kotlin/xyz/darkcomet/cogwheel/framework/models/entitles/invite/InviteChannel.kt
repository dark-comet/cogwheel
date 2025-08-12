package xyz.darkcomet.cogwheel.framework.models.entitles.invite

import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.ChannelType
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId

class InviteChannel(
    val id: ChannelId,
    val name: String,
    val type: ChannelType
) {
    companion object {
        internal fun from(obj: ChannelObject): InviteChannel {
            return obj.toInviteChannel()
        }
    }
}

internal fun ChannelObject.toInviteChannel(): InviteChannel {
    return InviteChannel(
        id = requireNonNull(this, ChannelObject::id).asChannelId(),
        name = requireNonNull(this, ChannelObject::name),
        type = requireNonNull(this, ChannelObject::type).let { ChannelType.fromOrAdd(it) }
    )
}