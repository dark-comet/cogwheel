package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ClientStatusObject(
    val desktop: String? = null,
    val mobile: String? = null,
    val web: String? = null
)