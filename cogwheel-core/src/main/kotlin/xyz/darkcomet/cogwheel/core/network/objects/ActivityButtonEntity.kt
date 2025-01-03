package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ActivityButtonEntity(
    val label: String,
    val url: String
)