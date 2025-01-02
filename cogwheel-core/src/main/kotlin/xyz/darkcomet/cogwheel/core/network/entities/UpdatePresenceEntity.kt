package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePresenceEntity(
    val since: Int?,
    val activities: ActivityEntity,
    val status: String,
    val afk: Boolean
)