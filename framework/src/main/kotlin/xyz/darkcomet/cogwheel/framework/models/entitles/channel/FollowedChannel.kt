@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.FollowedChannelObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.WebhookId
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId
import xyz.darkcomet.cogwheel.framework.primitives.asWebhookId

class FollowedChannel(
    val channelId: ChannelId,
    val webhookId: WebhookId
) {
    companion object {
        internal fun from(obj: FollowedChannelObject): FollowedChannel {
            return obj.toModel()
        }
    }
}

internal fun FollowedChannelObject.toModel(): FollowedChannel {
    return FollowedChannel(
        channelId = requireNonNull(this, FollowedChannelObject::channelId).asChannelId(),
        webhookId = requireNonNull(this, FollowedChannelObject::webhookId).asWebhookId()
    )
}