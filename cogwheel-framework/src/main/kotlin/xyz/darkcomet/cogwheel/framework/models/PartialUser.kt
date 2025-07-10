package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.network.objects.UserObject

open class PartialUser(
    open val username: String?,
    open val discriminator: String?,
) {
    companion object {
        internal fun from(obj: UserObject): PartialUser {
            return obj.toPartialUserModel()
        }
    }
}

internal fun UserObject.toPartialUserModel(): PartialUser {
    return PartialUser(
        username = requireNonNullIfPresent(this, UserObject::username),
        discriminator = requireNonNullIfPresent(this, UserObject::discriminator)
    )
}