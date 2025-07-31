package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GetInviteRequestSpec(internal val inviteCode: String) {
    
    internal var withCounts: Boolean? = null
    internal var withExpiration: Boolean? = null
    internal var guildScheduledEventId: Snowflake? = null
    
    fun withCounts(flag: Boolean): GetInviteRequestSpec
        = apply { this.withCounts = flag }
    
    fun withExpiration(flag: Boolean): GetInviteRequestSpec
        = apply { this.withExpiration = flag }
    
    fun guildScheduledEventId(id: Snowflake): GetInviteRequestSpec
        = apply { this.guildScheduledEventId = id }
    
}