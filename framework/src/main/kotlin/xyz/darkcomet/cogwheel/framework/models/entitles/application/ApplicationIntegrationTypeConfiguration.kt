package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull

class ApplicationIntegrationTypeConfiguration(
    val oauth2InstallParams: ApplicationInstallParameters
) {
    internal fun toObject(): ApplicationIntegrationTypeConfigurationObject {
        return ApplicationIntegrationTypeConfigurationObject(
            oauth2InstallParams = MaybeAbsent(oauth2InstallParams.toObject())
        )
    }
    
    companion object {
        internal fun from(obj: ApplicationIntegrationTypeConfigurationObject): ApplicationIntegrationTypeConfiguration {
            return obj.toModel();
        }
    }
}

internal fun ApplicationIntegrationTypeConfigurationObject.toModel(): ApplicationIntegrationTypeConfiguration {
    return ApplicationIntegrationTypeConfiguration(
        oauth2InstallParams = requireNonNull(
            this,
            ApplicationIntegrationTypeConfigurationObject::oauth2InstallParams
        ).toModel()
    )
}