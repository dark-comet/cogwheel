@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles

import xyz.darkcomet.cogwheel.core.network.objects.StageInstanceObject
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.*

class StageInstance(
    val id: StageInstanceId,
    val guildId: GuildId,
    val channelId: ChannelId,
    val topic: String,
    val privacyLevel: StageInstancePrivacyLevel,
    @Deprecated("to be removed by Discord API") val discoverableDisabled: Boolean,
    val guildScheduledEventId: GuildScheduledEventId?
) {
    companion object {
        internal fun from(obj: StageInstanceObject): StageInstance {
            return obj.toModel()
        }
    }
}

internal fun StageInstanceObject.toModel(): StageInstance {
    return StageInstance(
        id = requireNonNull(this, StageInstanceObject::id).asStageInstanceId(),
        guildId = requireNonNull(this, StageInstanceObject::guildId).asGuildId(),
        channelId = requireNonNull(this, StageInstanceObject::channelId).asChannelId(),
        topic = requireNonNull(this, StageInstanceObject::topic),
        privacyLevel = requireNonNull(this, StageInstanceObject::privacyLevel).let { StageInstancePrivacyLevel.fromOrAdd(it) },
        discoverableDisabled = requireNonNull(this, StageInstanceObject::discoverableDisabled),
        guildScheduledEventId = require(this, StageInstanceObject::guildScheduledEventId)?.asGuildScheduledEventId()
    )
}