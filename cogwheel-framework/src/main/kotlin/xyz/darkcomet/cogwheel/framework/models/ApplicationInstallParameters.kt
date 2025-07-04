package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.primitives.PermissionSet
import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.framework.primitives.OAuth2Scope

class ApplicationInstallParameters(
    val scopes: List<OAuth2Scope>,
    val permissions: PermissionSet
) {
    internal fun toObject(): ApplicationInstallParamsObject {
        return ApplicationInstallParamsObject(
            scopes = Possible(scopes.map { scope -> scope.value }), 
            permissions = Possible(permissions.toString())
        )  
    }
}