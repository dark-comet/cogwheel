package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData

@Serializable
data class CreateGuildFromGuildTemplateRequestParameters(
    val name: String,
    val icon: ImageData? = null
)