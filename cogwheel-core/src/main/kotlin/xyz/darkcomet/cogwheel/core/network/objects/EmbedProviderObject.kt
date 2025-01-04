package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class EmbedProviderObject(
    val name: String? = null,
    val url: String? = null
)