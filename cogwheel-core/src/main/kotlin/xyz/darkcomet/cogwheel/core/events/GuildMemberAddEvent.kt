package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject

class GuildMemberAddEvent
internal constructor(override val data: GuildMemberObject) : Event<GuildMemberObject>