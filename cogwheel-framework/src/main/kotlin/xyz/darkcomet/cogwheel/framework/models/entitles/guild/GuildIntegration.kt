package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildIntegrationObject

// TODO
class GuildIntegration {
    companion object {
        internal fun from(obj: GuildIntegrationObject): GuildScheduledEvent {
            return obj.toModel()
        }
    }
}

internal fun GuildIntegrationObject.toModel(): GuildScheduledEvent {
    TODO()
}

internal fun GuildIntegrationObject.toPartialModel(): PartialGuildIntegration {
    TODO()
}