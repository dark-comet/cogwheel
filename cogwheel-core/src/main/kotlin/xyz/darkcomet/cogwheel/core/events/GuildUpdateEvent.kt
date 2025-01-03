package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildEntity

data class GuildUpdateEvent(val data: GuildEntity) : Event