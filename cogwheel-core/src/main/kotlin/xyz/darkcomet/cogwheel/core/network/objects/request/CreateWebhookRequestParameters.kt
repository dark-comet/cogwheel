package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData

@Serializable
data class CreateWebhookRequestParameters(
    val name: String,
    val avatar: ImageData? = null,
)