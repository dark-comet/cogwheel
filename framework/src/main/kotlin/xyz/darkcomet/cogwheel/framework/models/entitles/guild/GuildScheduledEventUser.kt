@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventUserObject
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventId
import xyz.darkcomet.cogwheel.framework.primitives.asGuildScheduledEventId

class GuildScheduledEventUser(
    val eventId: GuildScheduledEventId,
    val user: User,
    val member: GuildMember?
) {
    companion object {
        internal fun from(obj: GuildScheduledEventUserObject): GuildScheduledEventUser {
            return obj.toModel()
        }
    }
}

internal fun GuildScheduledEventUserObject.toModel(): GuildScheduledEventUser {
    return GuildScheduledEventUser(
        eventId = requireNonNull(this, GuildScheduledEventUserObject::guildScheduledEventId).asGuildScheduledEventId(),
        user = requireNonNull(this, GuildScheduledEventUserObject::user).toModel(),
        member = requireNonNullIfPresent(this, GuildScheduledEventUserObject::member)?.toModel()
    )
}