package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ActivityObject

@Serializable
data class GatewayPresenceUpdateRequestParameters(
    val since: Int?,
    val activities: List<ActivityObject>,
    val status: String,
    val afk: Boolean
)