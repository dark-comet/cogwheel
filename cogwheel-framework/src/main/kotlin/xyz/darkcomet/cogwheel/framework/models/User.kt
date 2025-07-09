package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.entitles.user.UserAvatarDecorationData
import xyz.darkcomet.cogwheel.framework.models.entitles.user.UserCollectibles
import xyz.darkcomet.cogwheel.framework.models.entitles.user.UserPrimaryGuild
import xyz.darkcomet.cogwheel.framework.primitives.*

class User(
    val id: UserId,
    username: String,
    discriminator: String,
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
    val flags: UserFlag?,
    val premiumType: UserPremiumType?,
    val publicFlags: UserFlag?,
    val avatarDecorationData: UserAvatarDecorationData?,
    val collectibles: UserCollectibles?,
    val primaryGuild: UserPrimaryGuild?
) : PartialUser(username, discriminator)