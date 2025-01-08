package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.CreateTestEntitlementRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.EntitlementObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import java.util.stream.Collectors

class EntitlementResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listEntitlements(
        applicationId: Snowflake,
        userId: Snowflake? = null,
        skuIds: List<Snowflake>? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null,
        guildId: Snowflake? = null,
        excludeEnded: Boolean? = null,
        excludeDeleted: Boolean? = null
    ): CwHttpResponse<List<EntitlementObject>> {
        
        val httpRequest = CwHttpRequest.create(GET, "/applications/${applicationId}/entitlements") {
            optionalQueryStringParam("user_ids", userId)
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
            optionalQueryStringParam("guild_id", guildId)
            optionalQueryStringParam("exclude_ended", excludeEnded)
            optionalQueryStringParam("exclude_deleted", excludeDeleted)
            
            if (skuIds != null) {
                optionalQueryStringParam("sku_ids", skuIds.stream().map { it.toString() }.collect(Collectors.joining(",")))
            }
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(EntitlementObject.serializer()))
    }
    
    suspend fun getEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<EntitlementObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/applications/${applicationId}/entitlements/${entitlementId}",
            rateLimitRouteIdentifier = "/applications/${applicationId}/entitlements/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(EntitlementObject.serializer())
    }
    
    suspend fun consumeEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            POST, "/applications/${applicationId}/entitlements/${entitlementId}/consume",
            rateLimitRouteIdentifier = "/applications/${applicationId}/entitlements/*/consume"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun createTestEntitlement(
        applicationId: Snowflake, 
        request: CreateTestEntitlementRequestParameters
    ): CwHttpResponse<EntitlementObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/applications/${applicationId}/entitlements") {
            jsonParams(request, CreateTestEntitlementRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(EntitlementObject.serializer())
    }
    
    suspend fun deleteTestEntitlement(
        applicationId: Snowflake, 
        entitlementId: Snowflake
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/applications/${applicationId}/entitlements/${entitlementId}",
            rateLimitRouteIdentifier = "/applications/${applicationId}/entitlements/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}