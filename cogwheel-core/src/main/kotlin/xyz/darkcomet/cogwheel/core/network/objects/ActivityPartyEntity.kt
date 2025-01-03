package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ActivityPartyEntity(
    val id: String? = null,
    val size: List<Int>? = null
)
