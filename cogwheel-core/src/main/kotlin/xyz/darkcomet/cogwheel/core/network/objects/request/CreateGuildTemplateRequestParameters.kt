package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateGuildTemplateRequestParameters(
    val name: String,
    val description: String? = null
)