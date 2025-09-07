@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.models.entitles.user.UserAvatarDecorationData
import xyz.darkcomet.cogwheel.framework.models.entitles.user.toModel
import xyz.darkcomet.cogwheel.framework.primitives.*
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class GuildMember(
    val user: User?,
    val nickname: String?,
    val avatar: DiscordImageAlias?,
    val banner: DiscordImageAlias?,
    val roles: List<RoleId>,
    val joinedAt: ISO8601Timestamp?,
    val premiumSince: ISO8601Timestamp?,
    val deaf: Boolean,
    val mute: Boolean,
    val flags: BitField<GuildMemberFlag>,
    val pending: Boolean?,
    val permissions: BitField<Permission>?,
    val communicationDisabledUntil: ISO8601Timestamp?,
    val avatarDecorationData: UserAvatarDecorationData?,
    val guildId: GuildId?,
) {
    companion object {
        internal fun from(obj: GuildMemberObject): GuildMember {
            return obj.toModel()
        }
    }
}

internal fun GuildMemberObject.toModel(): GuildMember {
    return GuildMember(
        user = this.user?.value?.toModel(),
        nickname = this.nick?.value,
        avatar = this.avatar?.value?.let { DiscordImageAlias(ImageAliasType.GUILD_MEMBER_AVATAR, it) },
        banner = this.banner?.value?.let { DiscordImageAlias(ImageAliasType.GUILD_MEMBER_BANNER, it) },
        roles = requireNonNull(this, GuildMemberObject::roles).map { it.asRoleId() },
        joinedAt = require(this, GuildMemberObject::joinedAt),
        premiumSince = this.premiumSince?.value,
        deaf = requireNonNull(this, GuildMemberObject::deaf),
        mute = requireNonNull(this, GuildMemberObject::mute),
        flags = requireNonNull(this, GuildMemberObject::flags).let { BitField.from(it) },
        pending = requireNonNullIfPresent(this, GuildMemberObject::pending),
        permissions = requireNonNullIfPresent(this, GuildMemberObject::permissions)?.let { BitField.from(it) },
        communicationDisabledUntil = this.communicationDisabledUntil?.value,
        avatarDecorationData = this.avatarDecorationData?.value?.toModel(),
        guildId = this.guildId?.value?.asGuildId(),
    )
}