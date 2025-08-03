package xyz.darkcomet.cogwheel.framework.models.entitles.user

import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.framework.primitives.*

data class User(
    val id: UserId,
    override val username: String,
    override val discriminator: String,
    val globalName: String?,
    val avatar: String?,
    val bot: Boolean?,
    val system: Boolean?,
    val mfaEnabled: Boolean?,
    val banner: String?,
    val accentColor: DiscordColor?,
    val locale: DiscordLocale?,
    val verified: Boolean?,
    val email: String?,
    val flags: BitField<UserFlag>?,
    val premiumType: UserPremiumType?,
    val publicFlags: BitField<UserFlag>?,
    val avatarDecorationData: UserAvatarDecorationData?,
    val collectibles: UserCollectibles?,
    val primaryGuild: UserPrimaryGuild?
) : PartialUser(username, discriminator) {
    companion object {
        internal fun from(obj: UserObject): User {
            return obj.toModel()
        }
    }
}

internal fun UserObject.toModel(): User {
    TODO()
}