package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
class ModifyDmChannelRequestParameters(
    val name: String? = null,
    val icon: String? = null
)