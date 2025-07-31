@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.invite

import xyz.darkcomet.cogwheel.core.network.objects.InviteObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.entitles.application.Application
import xyz.darkcomet.cogwheel.framework.models.entitles.application.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEvent
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.PartialGuild
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toPartialModel
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.Bitmask
import xyz.darkcomet.cogwheel.framework.primitives.GuildInviteFlag
import xyz.darkcomet.cogwheel.framework.primitives.InviteTargetType
import xyz.darkcomet.cogwheel.framework.primitives.InviteType

@Suppress("DEPRECATION") // intentionally provided by the library
class Invite(
    val type: InviteType,
    val code: String,
    val guild: PartialGuild?,
    val channel: InviteChannel?,
    val inviter: User?,
    val targetType: InviteTargetType?,
    val targetUser: User?,
    val targetApplication: Application?,
    val approximatePresenceCount: Int?,
    val approximateMemberCount: Int?,
    val expiresAt: ISO8601Timestamp?,
    @Deprecated("to be removed by Discord API") val stageInstance: InviteStageInstance?,
    val guildScheduledEvent: GuildScheduledEvent?,
    val flags: Bitmask<GuildInviteFlag>?
) {
    companion object {
        internal fun from(obj:  InviteObject): Invite {
            return obj.toModel()
        }
    }
}

internal fun InviteObject.toModel(): Invite {
    return Invite(
        type = requireNonNull(this, InviteObject::type).let { InviteType.fromOrAdd(it) },
        code = requireNonNull(this, InviteObject::code),
        guild = requireNonNullIfPresent(this, InviteObject::guild)?.toPartialModel(),
        channel = require(this, InviteObject::channel)?.toInviteChannel(),
        inviter = requireNonNullIfPresent(this, InviteObject::inviter)?.toModel(),
        targetType = requireNonNullIfPresent(this, InviteObject::targetType)?.let { InviteTargetType.fromOrAdd(it) },
        targetUser = requireNonNullIfPresent(this, InviteObject::targetUser)?.toModel(),
        targetApplication = requireNonNullIfPresent(this, InviteObject::targetApplication)?.toModel(),
        approximateMemberCount = requireNonNullIfPresent(this, InviteObject::approximateMemberCount),
        approximatePresenceCount = requireNonNullIfPresent(this, InviteObject::approximatePresenceCount),
        expiresAt = this.expiresAt?.value,
        stageInstance = requireNonNullIfPresent(this, InviteObject::stageInstance)?.toModel(),
        guildScheduledEvent = requireNonNullIfPresent(this, InviteObject::guildScheduledEvent)?.toModel(),
        flags = requireNonNullIfPresent(this, InviteObject::flags)?.let { Bitmask.from(it) }
    )
}