package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class ModifyGuildTemplateRequestParameters(
    val name: String? = null,
    val description: String? = null
)