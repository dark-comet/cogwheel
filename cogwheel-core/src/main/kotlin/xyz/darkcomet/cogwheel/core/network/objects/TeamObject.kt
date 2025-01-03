package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class TeamObject(
    val icon: String?,
    val id: Snowflake,
    val members: List<TeamMemberObject>,
    val name: String,
    @SerialName("owner_user_id") val ownerUserId: Snowflake,
)