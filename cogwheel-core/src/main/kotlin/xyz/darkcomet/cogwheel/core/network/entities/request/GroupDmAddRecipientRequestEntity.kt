package xyz.darkcomet.cogwheel.core.network.entities.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDmAddRecipientRequestEntity(
    @SerialName("access_token") val accessToken: String,
    val nick: String
)