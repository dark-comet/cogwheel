package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.SubscriptionObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SubscriptionResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listSkuSubscriptions(skuId: Snowflake): CwHttpResponse<List<SubscriptionObject>> {
        TODO("To be implemented")
    }
    
    fun getSkuSubscription(
        skuId: Snowflake, 
        subscriptionId: Snowflake
    ): CwHttpResponse<SubscriptionObject> {
        TODO("To be implemented")
    }
    
}