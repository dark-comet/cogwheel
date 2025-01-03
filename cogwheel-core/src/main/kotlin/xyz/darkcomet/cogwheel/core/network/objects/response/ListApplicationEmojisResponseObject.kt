package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject

@Serializable
data class ListApplicationEmojisResponseObject(
    val items: List<EmojiObject>
)