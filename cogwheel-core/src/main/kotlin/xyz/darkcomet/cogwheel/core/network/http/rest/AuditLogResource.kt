package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.GuildAuditLogEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AuditLogResource internal constructor(httpClient: CwHttpClient) {

    fun getGuildAuditLog(
        guildId: Snowflake,
        userId: Snowflake? = null,
        actionType: Int? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null
    ): CwHttpResponse<GuildAuditLogEntity> {
        TODO("Not implemented yet")
    }
    
}