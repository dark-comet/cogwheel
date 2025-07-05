package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.primitives.PermissionSet
import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.OAuth2Scope

class ApplicationInstallParameters(
    val scopes: List<OAuth2Scope>,
    val permissions: PermissionSet
) {
    internal fun toObject(): ApplicationInstallParamsObject {
        return ApplicationInstallParamsObject(
            scopes = Possible(scopes.map { scope -> scope.key }), 
            permissions = Possible(permissions.toString())
        )
    }
    
    companion object {
        internal fun from(obj: ApplicationInstallParamsObject): ApplicationInstallParameters {
            return obj.toModel()
        }
    }
}

internal fun ApplicationInstallParamsObject.toModel(): ApplicationInstallParameters {
    return ApplicationInstallParameters(
        scopes = requireNonNull(this, ApplicationInstallParamsObject::scopes).map { OAuth2Scope.fromOrAdd(it) },
        permissions = requireNonNull(this, ApplicationInstallParamsObject::permissions).let { PermissionSet.from(it) }
    )
}