package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.DELETE
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.GET
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.InviteObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class InviteResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getInvite(
        inviteCode: String,
        withCounts: Boolean? = null,
        withExpiration: Boolean? = null,
        guildScheduledEventId: Snowflake? = null,
    ): CwHttpResponse<InviteObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/invites/${inviteCode}",
            rateLimitRouteIdentifier = "/invites/*"
        ) {
            optionalQueryStringParam("with_counts", withCounts)
            optionalQueryStringParam("with_expiration", withExpiration)
            optionalQueryStringParam("guild_scheduled_event_id", guildScheduledEventId)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(InviteObject.serializer())
    }
    
    suspend fun deleteInvite(inviteCode: String): CwHttpResponse<InviteObject> {
        val httpRequest = CwHttpRequest.create(
            DELETE, "/invites/${inviteCode}",
            rateLimitRouteIdentifier = "/invites/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(InviteObject.serializer())
    }
    
}