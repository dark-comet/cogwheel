package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionObject(
    val count: Int,
    @SerialName("count_details") val countDetails: ReactionCountDetailsObject,
    val me: Boolean,
    @SerialName("me_burst") val meBurst: Boolean,
    val emoji: EmojiObject,
    @SerialName("burst_colors") val burstColors: List<Int>
)