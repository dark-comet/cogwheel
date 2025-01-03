package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildEntity

data class GuildCreateEvent(val data: GuildEntity) : Event