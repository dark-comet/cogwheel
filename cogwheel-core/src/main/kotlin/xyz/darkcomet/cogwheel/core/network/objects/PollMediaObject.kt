package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class PollMediaObject(
    val text: String? = null,
    val emoji: EmojiObject? = null
)