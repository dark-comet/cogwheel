package xyz.darkcomet.cogwheel.core.network.entities.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.entities.ApplicationInstallParamsEntity
import xyz.darkcomet.cogwheel.core.network.entities.ApplicationIntegrationTypeConfigurationEntity

@Serializable
data class ModifyCurrentApplicationRequestEntity(
    @SerialName("custom_install_url") var customInstallUrl: String? = null,
    var description: String? = null,
    @SerialName("role_connection_verification_url") var roleConnectionVerificationUrl: String? = null,
    @SerialName("install_params") var installParams: ApplicationInstallParamsEntity? = null,
    @SerialName("integration_types_config") var integrationTypesConfig: Map<String, ApplicationIntegrationTypeConfigurationEntity>? = null,
    var flags: Int? = null,
    var icon: String? = null, // image data
    @SerialName("cover_image") var coverImage: String? = null, // image data
    @SerialName("interactions_endpoint_url") var interactionsEndpointUrl: String? = null,
    var tags: List<String>? = null
)