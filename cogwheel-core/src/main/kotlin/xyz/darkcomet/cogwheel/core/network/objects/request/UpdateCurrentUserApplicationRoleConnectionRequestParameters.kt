package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationRoleConnectionMetadataObject

@Serializable
data class UpdateCurrentUserApplicationRoleConnectionRequestParameters(
    @SerialName("platform_name") val platformName: String? = null,
    @SerialName("platform_username") val platformUsername: String? = null,
    val metadata: Map<String, ApplicationRoleConnectionMetadataObject>? = null
)