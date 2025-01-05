package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InviteStageInstanceObject(
    val members: List<GuildMemberObject>,
    @SerialName("participation_count") val participationCount: Int,
    @SerialName("speaker_count") val speakerCount: Int,
    val topic: String
)