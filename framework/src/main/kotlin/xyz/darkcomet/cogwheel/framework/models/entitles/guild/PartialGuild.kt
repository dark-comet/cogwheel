@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.framework.primitives.DiscordImage
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.GuildFeature
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.GuildNsfwLevel
import xyz.darkcomet.cogwheel.framework.primitives.GuildVerificationLevel
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId

class PartialGuild(
    val id: GuildId?,
    val name: String?,
    val description: String?,
    val splash: DiscordImage?,
    val banner: DiscordImage?,
    val features: List<GuildFeature>?,
    val icon: DiscordImage?,
    val verificationLevel: GuildVerificationLevel?,
    val vanityUrlCode: String?,
    val premiumSubscriptionCount: Int?,
    val nsfwLevel: GuildNsfwLevel?,
    val welcomeScreen: GuildWelcomeScreen?,
    val approximateMemberCount: Int?,
    val approximatePresenceCount: Int?
) {
    companion object {
        internal fun from(obj: GuildObject): PartialGuild {
            return obj.toPartialModel();
        }
    }
}

internal fun GuildObject.toPartialModel(): PartialGuild {
    return PartialGuild(
        id = requireNonNullIfPresent(this, GuildObject::id)?.asGuildId(),
        name = requireNonNullIfPresent(this, GuildObject::name),
        description = requireNonNullIfPresent(this, GuildObject::description),
        splash = requireNonNullIfPresent(this, GuildObject::splash)?.let { DiscordImage.fromDataUriScheme(it) },
        banner = requireNonNullIfPresent(this, GuildObject::banner)?.let { DiscordImage.fromDataUriScheme(it) },
        features = requireNonNullIfPresent(this, GuildObject::features)?.map { GuildFeature.fromOrAdd(it) },
        icon = requireNonNullIfPresent(this, GuildObject::icon)?.let { DiscordImage.fromDataUriScheme(it) },
        verificationLevel = requireNonNullIfPresent(this, GuildObject::verificationLevel)?.let{ GuildVerificationLevel.fromOrAdd(it) },
        vanityUrlCode = requireNonNullIfPresent(this, GuildObject::vanityUrlCode),
        premiumSubscriptionCount = requireNonNullIfPresent(this, GuildObject::premiumSubscriptionCount),
        nsfwLevel = requireNonNullIfPresent(this, GuildObject::nsfwLevel)?.let { GuildNsfwLevel.fromOrAdd(it) },
        welcomeScreen = requireNonNullIfPresent(this, GuildObject::welcomeScreen)?.toModel(),
        approximateMemberCount = requireNonNullIfPresent(this, GuildObject::approximateMemberCount),
        approximatePresenceCount = requireNonNullIfPresent(this, GuildObject::approximatePresenceCount)
    )
}