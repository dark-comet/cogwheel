package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject

data class GuildUpdateEvent
internal constructor(override val data: GuildObject) : Event<GuildObject>