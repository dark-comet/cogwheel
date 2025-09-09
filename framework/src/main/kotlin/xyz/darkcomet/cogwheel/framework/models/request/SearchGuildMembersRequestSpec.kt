package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class SearchGuildMembersRequestSpec(
    internal val guildId: GuildId
){
    internal var query: String? = null
    internal var limit: Int? = null

    fun query(query: String): SearchGuildMembersRequestSpec
        = apply { this.query = query }
    
    fun limit(limit: Int?): SearchGuildMembersRequestSpec
        = apply { this.limit = limit }
}