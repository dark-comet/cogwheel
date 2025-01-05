package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable

@Serializable
data class ModifyApplicationEmojiRequestParameters(
    val name: String
)