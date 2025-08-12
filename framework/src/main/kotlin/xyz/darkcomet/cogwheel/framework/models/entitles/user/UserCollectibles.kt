package xyz.darkcomet.cogwheel.framework.models.entitles.user

import xyz.darkcomet.cogwheel.core.network.objects.UserCollectiblesObject
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

data class UserCollectibles(
    val nameplate: UserNameplate?
) {
    companion object {
        internal fun from(obj: UserCollectiblesObject): UserCollectibles {
            return obj.toModel()
        }
    }
}

internal fun UserCollectiblesObject.toModel(): UserCollectibles {
    return UserCollectibles(
        nameplate = requireNonNullIfPresent(this, UserCollectiblesObject::nameplate)?.toModel(),
    )
}