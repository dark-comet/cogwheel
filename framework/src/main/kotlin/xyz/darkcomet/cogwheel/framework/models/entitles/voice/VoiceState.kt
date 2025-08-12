@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.voice

import xyz.darkcomet.cogwheel.core.network.objects.VoiceStateObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildMember
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.*

class VoiceState(
    val guildId: GuildId?,
    val channelId: ChannelId?,
    val userId: UserId,
    val member: GuildMember?,
    val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    val selfDeaf: Boolean,
    val selfMute: Boolean,
    val selfStream: Boolean?,
    val selfVideo: Boolean,
    val suppress: Boolean,
    val requestToSpeakTimestamp: ISO8601Timestamp?
) {
    companion object {
        internal fun from(obj: VoiceStateObject): VoiceState {
            return obj.toModel()
        }
    }
}

internal fun VoiceStateObject.toModel(): VoiceState {
    return VoiceState(
        guildId = requireNonNullIfPresent(this, VoiceStateObject::guildId)?.asGuildId(),
        channelId = require(this, VoiceStateObject::channelId)?.asChannelId(),
        userId = requireNonNull(this, VoiceStateObject::userId).asUserId(),
        member = requireNonNullIfPresent(this, VoiceStateObject::member)?.toModel(),
        sessionId = requireNonNull(this, VoiceStateObject::sessionId),
        deaf = requireNonNull(this, VoiceStateObject::deaf),
        mute = requireNonNull(this, VoiceStateObject::mute),
        selfDeaf = requireNonNull(this, VoiceStateObject::selfDeaf),
        selfMute = requireNonNull(this, VoiceStateObject::selfMute),
        selfStream = requireNonNullIfPresent(this, VoiceStateObject::selfStream),
        selfVideo = requireNonNull(this, VoiceStateObject::selfVideo),
        suppress = requireNonNull(this, VoiceStateObject::suppress),
        requestToSpeakTimestamp = require(this, VoiceStateObject::requestToSpeakTimestamp)
    )
}