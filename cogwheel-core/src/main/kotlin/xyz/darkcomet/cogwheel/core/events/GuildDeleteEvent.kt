package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.entities.GuildEntity

data class GuildDeleteEvent(val data: GuildEntity) : Event