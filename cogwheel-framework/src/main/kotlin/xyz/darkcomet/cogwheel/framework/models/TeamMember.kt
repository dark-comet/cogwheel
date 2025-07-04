package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.framework.primitives.TeamId
import xyz.darkcomet.cogwheel.framework.primitives.TeamMemberRole
import xyz.darkcomet.cogwheel.framework.primitives.TeamMembershipState

data class TeamMember(
    val membershipState: TeamMembershipState,
    val teamId: TeamId,
    val user: PartialUser,
    val role: TeamMemberRole
)