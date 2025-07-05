package xyz.darkcomet.cogwheel.framework.models.entitles.user

import xyz.darkcomet.cogwheel.core.network.objects.UserPrimaryGuildObject
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId

data class UserPrimaryGuild(
    val identityGuildId: GuildId?,
    val identityEnabled: Boolean?,
    val tag: String?,
    val badge: String?
) {
    companion object {
        internal fun from(obj: UserPrimaryGuildObject): UserPrimaryGuild {
            return obj.toModel()
        }
    }
}

internal fun UserPrimaryGuildObject.toModel(): UserPrimaryGuild {
    return UserPrimaryGuild(
        identityGuildId = this.identityGuildId?.value?.asGuildId(),
        identityEnabled = this.identityEnabled?.value,
        tag = this.tag?.value,
        badge = this.badge?.value
    )
}