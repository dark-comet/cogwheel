package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventUserObject
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildScheduledEventResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listScheduledEventsForGuild(guildId: Snowflake): CwHttpResponse<List<GuildScheduledEventObject>> {
        val httpRequest = CwHttpRequest.create(GET, "/guilds/${guildId}/scheduled-events")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildScheduledEventObject.serializer()))
    }
    
    suspend fun createGuildScheduledEvent(
        guildId: Snowflake,
        request: CreateGuildScheduledEventRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/guilds/${guildId}/scheduled-events") {
            jsonParams(request, CreateGuildScheduledEventRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildScheduledEventObject.serializer())
    }
    
    suspend fun getGuildScheduledEvent(
        guildId: Snowflake, 
        eventId: Snowflake,
        withUserCount: Boolean? = null
    ): CwHttpResponse<GuildScheduledEventObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/guilds/${guildId}/scheduled-events/${eventId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/scheduled-events/*"
        ) {
            optionalQueryStringParam("with_user_count", withUserCount)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildScheduledEventObject.serializer())
    }
    
    suspend fun modifyGuildScheduledEvent(
        guildId: Snowflake,
        eventId: Snowflake,
        request: ModifyGuildScheduledEventRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventObject> {
        
        val httpRequest = CwHttpRequest.create(
            PATCH, "/guilds/${guildId}/scheduled-events/${eventId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/scheduled-events/*"
        ) {
            jsonParams(request, ModifyGuildScheduledEventRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildScheduledEventObject.serializer())
    }
    
    suspend fun deleteGuildScheduledEvent(
        guildId: Snowflake, 
        eventId: Snowflake
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(
            DELETE, "/guilds/${guildId}/scheduled-events/${eventId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/scheduled-events/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getGuildScheduledEventUsers(
        guildId: Snowflake, 
        eventId: Snowflake,
        limit: Int? = null,
        withMember: Boolean? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
    ): CwHttpResponse<List<GuildScheduledEventUserObject>> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/guilds/${guildId}/scheduled-events/${eventId}/users",
            rateLimitRouteIdentifier = "/guilds/${guildId}/scheduled-events/*/users"
        ) {
            optionalQueryStringParam("limit", limit)
            optionalQueryStringParam("with_member", withMember)
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildScheduledEventUserObject.serializer()))
    }
    
}