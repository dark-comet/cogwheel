package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class GetGuildRequestSpec(
    internal val guildId: GuildId
) {
    internal var withCounts: Boolean = false
    
    fun withCounts(counts: Boolean): GetGuildRequestSpec
        = apply { this.withCounts = counts }
}