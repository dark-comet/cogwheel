package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class EmbedFieldObject(
    val name: String,
    val value: String,
    val inline: Boolean? = null
)