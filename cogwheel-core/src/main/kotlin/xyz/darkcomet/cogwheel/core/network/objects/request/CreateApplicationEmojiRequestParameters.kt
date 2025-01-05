package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData

@Serializable
data class CreateApplicationEmojiRequestParameters(
    val name: String,
    val image: ImageData
)