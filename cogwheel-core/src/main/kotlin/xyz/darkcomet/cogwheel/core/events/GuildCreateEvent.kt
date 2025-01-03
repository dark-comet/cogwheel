package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject

data class GuildCreateEvent(val data: GuildObject) : Event