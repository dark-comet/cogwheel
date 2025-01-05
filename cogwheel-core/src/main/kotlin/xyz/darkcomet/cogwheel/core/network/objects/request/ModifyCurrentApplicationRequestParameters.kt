package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.primitives.ImageData

@Serializable
data class ModifyCurrentApplicationRequestParameters(
    @SerialName("custom_install_url") var customInstallUrl: String? = null,
    var description: String? = null,
    @SerialName("role_connection_verification_url") var roleConnectionVerificationUrl: String? = null,
    @SerialName("install_params") var installParams: ApplicationInstallParamsObject? = null,
    @SerialName("integration_types_config") var integrationTypesConfig: Map<String, ApplicationIntegrationTypeConfigurationObject>? = null,
    var flags: Int? = null,
    var icon: ImageData? = null, // image data
    @SerialName("cover_image") var coverImage: ImageData? = null, // image data
    @SerialName("interactions_endpoint_url") var interactionsEndpointUrl: String? = null,
    var tags: List<String>? = null
)