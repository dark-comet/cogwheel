@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ThreadMemberObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildMember
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.*

sealed class AbstractThreadMember(
    val joinTimestamp: ISO8601Timestamp,
    val flags: BitField<ChannelFlag>,
    val member: GuildMember?,
)

class PartialThreadMember(
    joinTimestamp: ISO8601Timestamp,
    flags: BitField<ChannelFlag>,
    member: GuildMember?,
) : AbstractThreadMember(joinTimestamp, flags, member) {
    companion object {
        internal fun from(obj: ThreadMemberObject): PartialThreadMember {
            return obj.toPartialModel()
        }
    }
}

internal fun ThreadMemberObject.toPartialModel(): PartialThreadMember {
    return PartialThreadMember(
        joinTimestamp = requireNonNull(this, ThreadMemberObject::joinTimestamp),
        flags = requireNonNull(this, ThreadMemberObject::flags).let { BitField.from(it) },
        member = requireNonNullIfPresent(this, ThreadMemberObject::member)?.toModel()
    )
}

class ThreadMember(
    val id: ChannelId,
    val userId: UserId,
    joinTimestamp: ISO8601Timestamp,
    flags: BitField<ChannelFlag>,
    member: GuildMember?,
    
) : AbstractThreadMember(joinTimestamp, flags, member) {
    companion object {
        internal fun from(obj: ThreadMemberObject): ThreadMember {
            return obj.toModel()
        }
    }
}

internal fun ThreadMemberObject.toModel(): ThreadMember {
    return ThreadMember(
        id = requireNonNull(this, ThreadMemberObject::id).asChannelId(),
        userId = requireNonNull(this, ThreadMemberObject::userId).asUserId(),
        joinTimestamp = requireNonNull(this, ThreadMemberObject::joinTimestamp),
        flags = requireNonNull(this, ThreadMemberObject::flags).let { BitField.from(it) },
        member = requireNonNullIfPresent(this, ThreadMemberObject::member)?.toModel()
    )
}