package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageActivityObject(
    val type: Int,
    @SerialName("party_id") val partyId: String
)