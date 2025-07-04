package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.framework.primitives.DiscordColor
import xyz.darkcomet.cogwheel.framework.primitives.DiscordLocale
import xyz.darkcomet.cogwheel.framework.primitives.UserFlags
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.UserPremiumType

//class User(
//    id: UserId,
//    username: String,
//    discriminator: String,
//    globalName: String?,
//    avatar: String?,
//    val bot: Possible<Boolean>?,
//    val system: Possible<Boolean>?,
//    val mfaEnabled: Possible<Boolean>?,
//    val banner: Possible<String>?,
//    val accentColor: Possible<DiscordColor?>?,
//    val locale: Possible<DiscordLocale>?,
//    val verified: Possible<Boolean>?,
//    val email: Possible<String?>?,
//    val flags: Possible<UserFlags>?,
//    val premiumType: Possible<UserPremiumType>,
//    val publicFlags: Possible<UserPublicFlags>,
//    val avatarDecorationData: Possible<UserAvatarDecorationData?>?,
//    val collectibles: Possible<UserCollectibles?>?,
//    val primaryGuild: Possible<UserPrimaryGuild?>?
//) : PartialUser(id, username, discriminator, globalName, avatar);