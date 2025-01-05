package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class ModifyGuildStickerRequestParameters(
    val name: String? = null,
    val description: String? = null,
    val tags: String? = null
)