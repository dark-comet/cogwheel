package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildWelcomeScreenObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull

data class GuildWelcomeScreen(
    val description: String,
    val welcomeChannels: List<GuildWelcomeScreenChannel>
) {
    companion object {
        internal fun from(obj: GuildWelcomeScreenObject): GuildWelcomeScreen {
            return obj.toModel()
        }
    }
}

internal fun GuildWelcomeScreenObject.toModel(): GuildWelcomeScreen {
    return GuildWelcomeScreen(
        description = requireNonNull(this, GuildWelcomeScreenObject::description),
        welcomeChannels = requireNonNull(this, GuildWelcomeScreenObject::welcomeChannels).map { it.toModel() },
    )
}