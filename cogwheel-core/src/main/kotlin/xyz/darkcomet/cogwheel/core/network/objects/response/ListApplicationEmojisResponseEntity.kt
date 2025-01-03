package xyz.darkcomet.cogwheel.core.network.objects.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiEntity

@Serializable
data class ListApplicationEmojisResponseEntity(
    val items: List<EmojiEntity>
)