package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelDefaultReactionObject(
    @SerialName("emoji_id") val emojiId: String?,
    @SerialName("emoji_name") val emojiName: String?
)