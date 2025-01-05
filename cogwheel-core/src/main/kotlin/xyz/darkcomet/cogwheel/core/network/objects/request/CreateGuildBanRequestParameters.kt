package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateGuildBanRequestParameters(
    @SerialName("delete_message_days") val deleteMessageDays: Int? = null,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Int? = null,
)