package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.primitives.Possible
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
    val bot: Possible<Boolean>?,
    val system: Possible<Boolean>?,
    val mfaEnabled: Possible<Boolean>?,
    val banner: Possible<String>?,
    val accentColor: Possible<DiscordColor?>?,
    val locale: Possible<DiscordLocale>?,
    val verified: Possible<Boolean>?,
    val email: Possible<String?>?,
    val flags: Possible<UserFlag>?,
    val premiumType: Possible<UserPremiumType>,
    val publicFlags: Possible<UserFlag>,
    val avatarDecorationData: Possible<UserAvatarDecorationData?>?,
    val collectibles: Possible<UserCollectibles?>?,
    val primaryGuild: Possible<UserPrimaryGuild?>?
) : PartialUser(username, discriminator)