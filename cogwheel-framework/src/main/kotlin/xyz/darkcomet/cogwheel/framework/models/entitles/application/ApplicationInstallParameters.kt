package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.Bitmask
import xyz.darkcomet.cogwheel.framework.primitives.OAuth2Scope
import xyz.darkcomet.cogwheel.framework.primitives.Permission
import java.math.BigInteger

class ApplicationInstallParameters(
    val scopes: List<OAuth2Scope>,
    val permissions: Bitmask<Permission>
) {
    internal fun toObject(): ApplicationInstallParamsObject {
        return ApplicationInstallParamsObject(
            scopes = MaybeAbsent(scopes.map { scope -> scope.key }), 
            permissions = MaybeAbsent(permissions.toString())
        )
    }
    
    companion object {
        fun builder(): BuilderSpec = BuilderSpec()
        
        internal fun from(obj: ApplicationInstallParamsObject): ApplicationInstallParameters {
            return obj.toModel()
        }
    }
    
    class BuilderSpec {
        private var scopes: List<OAuth2Scope> = ArrayList(1)
        private var permissions: Bitmask<Permission> = Bitmask(BigInteger("0", 10))
        
        fun scopes(vararg scopes: OAuth2Scope): BuilderSpec
            = apply { this.scopes = scopes.toList() }
        
        fun permissions(vararg permissions: Permission): BuilderSpec 
            = apply { this.permissions = Bitmask.from(*permissions) }
        
        fun permissions(permissions: Bitmask<Permission>): BuilderSpec
            = apply { this.permissions = permissions }
        
        fun build(): ApplicationInstallParameters {
            return ApplicationInstallParameters(scopes, permissions)
        }
    }
}

internal fun ApplicationInstallParamsObject.toModel(): ApplicationInstallParameters {
    return ApplicationInstallParameters(
        scopes = requireNonNull(this, ApplicationInstallParamsObject::scopes).map { OAuth2Scope.fromOrAdd(it) },
        permissions = requireNonNull(this, ApplicationInstallParamsObject::permissions).let { Bitmask.from(it) }
    )
}