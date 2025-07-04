package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.primitives.Possible

class ApplicationIntegrationTypeConfiguration(
    val oauth2InstallParams: ApplicationInstallParameters
) {
    internal fun toObject(): ApplicationIntegrationTypeConfigurationObject {
        return ApplicationIntegrationTypeConfigurationObject(
            oauth2InstallParams = Possible(oauth2InstallParams.toObject())
        )
    }
}