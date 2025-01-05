package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.SubscriptionObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SubscriptionResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listSkuSubscriptions(skuId: Snowflake): CwHttpResponse<List<SubscriptionObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/skus/${skuId}/subscriptions")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(SubscriptionObject.serializer()))
    }
    
    suspend fun getSkuSubscription(
        skuId: Snowflake, 
        subscriptionId: Snowflake
    ): CwHttpResponse<SubscriptionObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/skus/${skuId}/subscriptions/${subscriptionId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(SubscriptionObject.serializer())
    }
    
}