package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildAuditLogObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AuditLogResource 
internal constructor(private val httpClient: CwHttpClient) {

    suspend fun getGuildAuditLog(
        guildId: Snowflake,
        userId: Snowflake? = null,
        actionType: Int? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null
    ): CwHttpResponse<GuildAuditLogObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/audit-logs") {
            optionalQueryStringParam("user_id", userId)
            optionalQueryStringParam("action_type", actionType)
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withDataObject(GuildAuditLogObject.serializer())
    }
    
}