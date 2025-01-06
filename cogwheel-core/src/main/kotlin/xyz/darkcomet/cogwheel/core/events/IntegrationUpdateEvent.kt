package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildIntegrationObject

class IntegrationUpdateEvent
internal constructor(override val data: GuildIntegrationObject) : Event<GuildIntegrationObject>