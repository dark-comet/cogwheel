package xyz.darkcomet.cogwheel.core.network.entities.response

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.entities.EmojiEntity

@Serializable
data class ListApplicationEmojisResponseEntity(
    val items: List<EmojiEntity>
)