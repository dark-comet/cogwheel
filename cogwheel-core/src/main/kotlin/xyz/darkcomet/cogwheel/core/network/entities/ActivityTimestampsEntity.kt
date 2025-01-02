package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class ActivityTimestampsEntity(
    val start: Long? = null,
    val end: Long? = null
)