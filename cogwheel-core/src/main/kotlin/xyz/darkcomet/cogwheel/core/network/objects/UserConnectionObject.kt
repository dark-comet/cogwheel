package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserConnectionObject(
    val id: String,
    val name: String,
    val type: String,
    val revoked: Boolean? = null,
    val integrations: List<GuildIntegrationObject>? = null,
    val verified: Boolean,
    @SerialName("friend_sync") val friendSync: Boolean,
    @SerialName("show_activity") val showActivity: Boolean,
    @SerialName("two_way_link") val twoWayLink: Boolean,
    val visibility: Int
)