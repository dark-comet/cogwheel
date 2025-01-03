package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDmAddRecipientRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: String
)