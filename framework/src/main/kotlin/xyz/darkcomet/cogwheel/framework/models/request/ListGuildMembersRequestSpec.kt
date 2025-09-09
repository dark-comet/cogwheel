package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ListGuildMembersRequestSpec(
    internal val guildId: GuildId
) {
    internal var limit: Int? = null
    internal var after: Snowflake? = null
    
    fun limit(limit: Int?): ListGuildMembersRequestSpec
        = apply { this.limit = limit }
    
    fun after(after: UserId?): ListGuildMembersRequestSpec
        = apply { this.after = after?.snowflake }
}