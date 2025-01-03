package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ActivityTimestampsEntity(
    val start: Long? = null,
    val end: Long? = null
)