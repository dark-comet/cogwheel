package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApplicationRoleConnectionObject(
    @SerialName("platform_name") val platformName: String?,
    @SerialName("platform_username") val platformUsername: String?,
    val metadata: Map<String, ApplicationRoleConnectionMetadataObject>
)