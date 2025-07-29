package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventId

class GetGuildScheduledEventRequestSpec(
    internal var guildId: GuildId,
    internal var eventId: GuildScheduledEventId
) {
    
    internal var withUserCount: Boolean? = null
    
    fun withUserCount(flag: Boolean): GetGuildScheduledEventRequestSpec
        = apply { this.withUserCount = flag }
    
}