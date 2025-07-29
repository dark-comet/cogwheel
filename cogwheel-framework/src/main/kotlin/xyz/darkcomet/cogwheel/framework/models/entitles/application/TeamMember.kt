@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.TeamMemberObject
import xyz.darkcomet.cogwheel.framework.models.PartialUser
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.toPartialUserModel
import xyz.darkcomet.cogwheel.framework.primitives.TeamId
import xyz.darkcomet.cogwheel.framework.primitives.TeamMemberRole
import xyz.darkcomet.cogwheel.framework.primitives.TeamMembershipState
import xyz.darkcomet.cogwheel.framework.primitives.asTeamId

class TeamMember(
    val membershipState: TeamMembershipState,
    val teamId: TeamId,
    val user: PartialUser,
    val role: TeamMemberRole
) {
    companion object {
        internal fun from(obj: TeamMemberObject): TeamMember {
            return obj.toModel()
        }
    }
}

internal fun TeamMemberObject.toModel(): TeamMember {
    return TeamMember(
        membershipState = requireNonNull(this, TeamMemberObject::membershipState).let { TeamMembershipState.fromOrAdd(it) },
        teamId = requireNonNull(this, TeamMemberObject::teamId).asTeamId(),
        user = requireNonNull(this, TeamMemberObject::user).toPartialUserModel(),
        role = requireNonNull(this, TeamMemberObject::role).let { TeamMemberRole.fromOrAdd(it) }
    )
}