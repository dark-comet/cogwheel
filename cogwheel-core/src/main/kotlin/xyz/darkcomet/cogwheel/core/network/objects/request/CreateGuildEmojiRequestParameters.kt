package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateGuildEmojiRequestParameters(
    val name: String,
    val image: ImageData,
    val roles: List<Snowflake>
)