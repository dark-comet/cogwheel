package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class EditChannelPermissionsParameters(
    val allow: String? = null,
    val deny: String? = null,
    val type: Int
)