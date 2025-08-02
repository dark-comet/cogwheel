package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.GET
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.SubscriptionObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SubscriptionResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listSkuSubscriptions(
        skuId: Snowflake,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null,
        userId: Snowflake? = null,
    ): CwHttpResponse<List<SubscriptionObject>> {
        val httpRequest = CwHttpRequest.create(GET, "/skus/${skuId}/subscriptions") {
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
            optionalQueryStringParam("user_id", userId)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(SubscriptionObject.serializer()))
    }
    
    suspend fun getSkuSubscription(
        skuId: Snowflake, 
        subscriptionId: Snowflake
    ): CwHttpResponse<SubscriptionObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/skus/${skuId}/subscriptions/${subscriptionId}",
            rateLimitRouteIdentifier = "/skus/${skuId}/subscriptions/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(SubscriptionObject.serializer())
    }
    
}