package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class AutoModerationActionObject(
    val type: Int,
    val metadata: AutoModerationActionMetadataObject? = null
)