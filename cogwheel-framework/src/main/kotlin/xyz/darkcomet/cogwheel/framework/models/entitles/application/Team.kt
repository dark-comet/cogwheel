@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.TeamObject
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.TeamId
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asTeamId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId

class Team(
    val icon: DiscordImage?,
    val id: TeamId,
    val members: List<TeamMember>,
    val name: String,
    val ownerUserId: UserId
) {
    companion object {
        internal fun from(obj: TeamObject): Team {
            return obj.toModel()
        }
    }
}

internal fun TeamObject.toModel(): Team {
    return Team(
        icon = require(this, TeamObject::icon)?.let { DiscordImage.fromDataUriScheme(it) },
        id = requireNonNull(this, TeamObject::id).asTeamId(),
        members = requireNonNull(this, TeamObject::members).map { it.toModel() },
        name = requireNonNull(this, TeamObject::name),
        ownerUserId = requireNonNull(this, TeamObject::ownerUserId).asUserId()
    )
}