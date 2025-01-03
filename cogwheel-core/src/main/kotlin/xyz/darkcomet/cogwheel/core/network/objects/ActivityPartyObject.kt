package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ActivityPartyObject(
    val id: String? = null,
    val size: List<Int>? = null
)
