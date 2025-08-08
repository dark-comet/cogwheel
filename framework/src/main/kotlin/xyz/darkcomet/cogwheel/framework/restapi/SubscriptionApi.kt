@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.SubscriptionResource
import xyz.darkcomet.cogwheel.framework.models.entitles.Subscription
import xyz.darkcomet.cogwheel.framework.models.entitles.toModel
import xyz.darkcomet.cogwheel.framework.models.request.ListSkuSubscriptionsRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.concurrent.Future
import java.util.function.Consumer

class SubscriptionApi
internal constructor(resource: SubscriptionResource) {
    
    @JvmField
    val list = object : RequestInvocation1S<SkuId, ListSkuSubscriptionsRequestSpec, List<Subscription>>() {
        
        override fun createRequest(p1: SkuId): ListSkuSubscriptionsRequestSpec {
            return ListSkuSubscriptionsRequestSpec(p1)
        }

        override suspend fun invoke(request: ListSkuSubscriptionsRequestSpec): Response<List<Subscription>> {
            val response = resource.listSkuSubscriptions(request.skuId.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val get = object : Invocation2<SkuId, SubscriptionId, Subscription>() {
        
        override suspend fun invoke(
            p1: SkuId,
            p2: SubscriptionId
        ): Response<Subscription> {
            val response = resource.getSkuSubscription(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
}