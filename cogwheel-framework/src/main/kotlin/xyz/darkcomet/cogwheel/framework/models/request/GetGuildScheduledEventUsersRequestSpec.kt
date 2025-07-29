package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.GuildScheduledEventId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GetGuildScheduledEventUsersRequestSpec(
    internal var guildId: GuildId,
    internal var eventId: GuildScheduledEventId
) {
    internal var limit: Int? = null
    internal var withMember: Boolean? = null
    internal var before: Snowflake? = null
    internal var after: Snowflake? = null
    
    fun limit(quantity: Int): GetGuildScheduledEventUsersRequestSpec
        = apply { this.limit = quantity }
    
    fun withMember(flag: Boolean): GetGuildScheduledEventUsersRequestSpec
        = apply { this.withMember = flag }
    
    fun before(userId: UserId): GetGuildScheduledEventUsersRequestSpec
        = apply { this.before = userId.snowflake }
    
    fun after(userId: UserId): GetGuildScheduledEventUsersRequestSpec
        = apply { this.after = userId.snowflake }
    
}