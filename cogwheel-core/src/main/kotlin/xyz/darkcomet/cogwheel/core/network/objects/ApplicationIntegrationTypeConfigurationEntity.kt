package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationIntegrationTypeConfigurationEntity(
    @SerialName("oauth2_install_params") val oauth2InstallParams: ApplicationInstallParamsEntity? = null
)