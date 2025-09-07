@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.Emoji
import xyz.darkcomet.cogwheel.framework.models.entitles.emoji.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.Sticker
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.toModel
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.DiscordLocale
import xyz.darkcomet.cogwheel.framework.primitives.GuildDefaultMessageNotificationLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildExplicitContentFilterLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildFeature
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.GuildMfaLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildNsfwLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildPremiumTier
import xyz.darkcomet.cogwheel.framework.primitives.GuildVerificationLevel
import xyz.darkcomet.cogwheel.framework.primitives.Permission
import xyz.darkcomet.cogwheel.framework.primitives.SystemChannelFlag
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class Guild(
    val id: GuildId,
    val name: String,
    val icon: String?,
    val iconHash: String?,
    val splash: String?,
    val discoverySplash: String?,
    val owner: Boolean?,
    val ownerId: UserId,
    val permissions: BitField<Permission>?,
    @Deprecated("Deprecated by Discord API") val region: String?,
    val afkChannelId: ChannelId?,
    val afkTimeout: Int,
    val widgetEnabled: Boolean?,
    val widgetChannelId: ChannelId?,
    val verificationLevel: GuildVerificationLevel,
    val defaultMessageNotifications: GuildDefaultMessageNotificationLevel,
    val explicitContentFilter: GuildExplicitContentFilterLevel,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<GuildFeature>,
    val mfaLevel: GuildMfaLevel,
    val applicationId: ApplicationId?,
    val systemChannelId: ChannelId?,
    val systemChannelFlags: BitField<SystemChannelFlag>,
    val rulesChannelId: ChannelId?,
    val maxPresences: Int?,
    val maxMembers: Int?,
    val vanityUrlCode: String?,
    val description: String?,
    val banner: String?,
    val premiumTier: GuildPremiumTier,
    val premiumSubscriptionCount: Int?,
    val preferredLocale: DiscordLocale,
    val publicUpdatesChannelId: ChannelId?,
    val maxVideoChannelUsers: Int?,
    val maxStageVideoChannelUsers: Int?,
    val approximateMemberCount: Int?,
    val approximatePresenceCount: Int?,
    val welcomeScreen: GuildWelcomeScreen?,
    val nsfwLevel: GuildNsfwLevel?,
    val stickers: List<Sticker>?,
    val premiumProgressBarEnabled: Boolean,
    val safetyAlertsChannelId: ChannelId?,
    val incidentsData: GuildIncidentsData?
) {
    companion object {
        internal fun from(obj: GuildObject): Guild {
            return obj.toModel();
        }
    }
}

internal fun GuildObject.toModel(): Guild {
    return Guild(
        id = requireNonNull(this, GuildObject::id).asGuildId(),
        name = requireNonNull(this, GuildObject::name),
        icon = require(this, GuildObject::icon),
        iconHash = this.iconHash?.value,
        splash = require(this, GuildObject::splash),
        discoverySplash = require(this, GuildObject::discoverySplash),
        owner = requireNonNullIfPresent(this, GuildObject::owner),
        ownerId = requireNonNull(this, GuildObject::ownerId).asUserId(),
        permissions = requireNonNullIfPresent(this, GuildObject::permissions)?.let { BitField.from(it) },
        region = this.region?.value,
        afkChannelId = require(this, GuildObject::afkChannelId)?.asChannelId(),
        afkTimeout = requireNonNull(this, GuildObject::afkTimeout),
        widgetEnabled = requireNonNullIfPresent(this, GuildObject::widgetEnabled),
        widgetChannelId = this.widgetChannelId?.value?.asChannelId(),
        verificationLevel = requireNonNull(this, GuildObject::verificationLevel).let { GuildVerificationLevel.fromOrAdd(it) },
        defaultMessageNotifications = requireNonNull(this, GuildObject::defaultMessageNotifications).let { GuildDefaultMessageNotificationLevel.fromOrAdd(it) },
        explicitContentFilter = requireNonNull(this, GuildObject::explicitContentFilter).let { GuildExplicitContentFilterLevel.fromOrAdd(it) },
        roles = requireNonNull(this, GuildObject::roles).map { it.toModel() },
        emojis = requireNonNull(this, GuildObject::emojis).map { it.toModel() },
        features = requireNonNull(this, GuildObject::features).map { GuildFeature.fromOrAdd(it) },
        mfaLevel = requireNonNull(this, GuildObject::mfaLevel).let { GuildMfaLevel.fromOrAdd(it) },
        applicationId = require(this, GuildObject::applicationId)?.asApplicationId(),
        systemChannelId = require(this, GuildObject::systemChannelId)?.asChannelId(),
        systemChannelFlags = requireNonNull(this, GuildObject::systemChannelFlags).let { BitField.from(it) },
        rulesChannelId = require(this, GuildObject::rulesChannelId)?.asChannelId(),
        maxPresences = this.maxPresences?.value,
        maxMembers = requireNonNullIfPresent(this, GuildObject::maxMembers),
        vanityUrlCode = require(this, GuildObject::vanityUrlCode),
        description = require(this, GuildObject::description),
        banner = require(this, GuildObject::banner),
        premiumTier = requireNonNull(this, GuildObject::premiumTier).let { GuildPremiumTier.fromOrAdd(it) },
        premiumSubscriptionCount = requireNonNullIfPresent(this, GuildObject::premiumSubscriptionCount),
        preferredLocale = requireNonNull(this, GuildObject::preferredLocale).let { DiscordLocale.fromOrAdd(it) },
        publicUpdatesChannelId = require(this, GuildObject::publicUpdatesChannelId)?.asChannelId(),
        maxVideoChannelUsers = requireNonNullIfPresent(this, GuildObject::maxVideoChannelUsers),
        maxStageVideoChannelUsers = requireNonNullIfPresent(this, GuildObject::maxStageVideoChannelUsers),
        approximateMemberCount = requireNonNullIfPresent(this, GuildObject::approximateMemberCount),
        approximatePresenceCount = requireNonNullIfPresent(this, GuildObject::approximatePresenceCount),
        welcomeScreen = requireNonNullIfPresent(this, GuildObject::welcomeScreen)?.toModel(),
        nsfwLevel = requireNonNull(this, GuildObject::nsfwLevel).let { GuildNsfwLevel.fromOrAdd(it) },
        stickers = requireNonNullIfPresent(this, GuildObject::stickers)?.map { it.toModel() },
        premiumProgressBarEnabled = requireNonNull(this, GuildObject::premiumProgressBarEnabled),
        safetyAlertsChannelId = require(this, GuildObject::safetyAlertsChannelId)?.asChannelId(),
        incidentsData = require(this, GuildObject::incidentsData)?.toModel()
    )
}