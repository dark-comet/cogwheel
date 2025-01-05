package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData

@Serializable
data class ModifyCurrentUserRequestParameters(
    val username: String,
    val avatar: ImageData?,
    val banner: ImageData?
)