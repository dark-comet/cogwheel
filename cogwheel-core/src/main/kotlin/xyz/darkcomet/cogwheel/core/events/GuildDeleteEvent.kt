package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject

data class GuildDeleteEvent(val data: GuildObject) : Event