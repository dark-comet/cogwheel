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
    val bot: MaybeAbsent<Boolean>?,
    val system: MaybeAbsent<Boolean>?,
    val mfaEnabled: MaybeAbsent<Boolean>?,
    val banner: MaybeAbsent<String>?,
    val accentColor: MaybeAbsent<DiscordColor?>?,
    val locale: MaybeAbsent<DiscordLocale>?,
    val verified: MaybeAbsent<Boolean>?,
    val email: MaybeAbsent<String?>?,
    val flags: MaybeAbsent<UserFlag>?,
    val premiumType: MaybeAbsent<UserPremiumType>,
    val publicFlags: MaybeAbsent<UserFlag>,
    val avatarDecorationData: MaybeAbsent<UserAvatarDecorationData?>?,
    val collectibles: MaybeAbsent<UserCollectibles?>?,
    val primaryGuild: MaybeAbsent<UserPrimaryGuild?>?
) : PartialUser(username, discriminator)