package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamMemberObject(
    @SerialName("membership_state") val membershipState: Int,
    @SerialName("team_id") val teamId: Int,
    val user: UserObject,
    val role: String
)