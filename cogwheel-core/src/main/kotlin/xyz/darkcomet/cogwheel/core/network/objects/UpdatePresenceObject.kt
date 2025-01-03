package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePresenceObject(
    val since: Int?,
    val activities: ActivityObject,
    val status: String,
    val afk: Boolean
)