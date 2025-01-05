package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModifyStageInstanceRequestParameters(
    val topic: String? = null,
    @SerialName("privacy_level") val privacyLevel: Int? = null,
)