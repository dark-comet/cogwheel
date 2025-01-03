package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamMemberEntity(
    @SerialName("membership_state") val membershipState: Int,
    @SerialName("team_id") val teamId: Int,
    val user: UserEntity,
    val role: String
)