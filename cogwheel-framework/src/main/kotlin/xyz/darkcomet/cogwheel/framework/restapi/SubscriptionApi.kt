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
    val list = ListSkuSubscriptionsEndpoint(resource)
    
    @JvmField
    val get = GetSkuSubscriptionsEndpoint(resource)
    
}

class ListSkuSubscriptionsEndpoint
internal constructor(private val resource: SubscriptionResource)
    : RequestInvocation1S<SkuId, ListSkuSubscriptionsRequestSpec, List<Subscription>>() {
        
    override fun createRequest(skuId: SkuId): ListSkuSubscriptionsRequestSpec {
        return ListSkuSubscriptionsRequestSpec(skuId)
    }

    override suspend fun invoke(request: ListSkuSubscriptionsRequestSpec): Response<List<Subscription>> {
        val response = resource.listSkuSubscriptions(request.skuId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

    override suspend fun invoke(
        skuId: SkuId,
        request: ListSkuSubscriptionsRequestSpec?,
        config: (ListSkuSubscriptionsRequestSpec.() -> Unit)?
    ): Response<List<Subscription>> {
        return super.invoke(skuId, request, config)
    }

    override fun async(
        skuId: SkuId,
        config: Consumer<ListSkuSubscriptionsRequestSpec>?
    ): Future<Response<List<Subscription>>> {
        return super.async(skuId, config)
    }

    override fun block(
        skuId: SkuId,
        config: Consumer<ListSkuSubscriptionsRequestSpec>?
    ): Response<List<Subscription>> {
        return super.block(skuId, config)
    }
}

class GetSkuSubscriptionsEndpoint
internal constructor(private val resource: SubscriptionResource)
    : Invocation2<SkuId, SubscriptionId, Subscription>() {
        
    override suspend fun invoke(
        skuId: SkuId,
        subscriptionId: SubscriptionId
    ): Response<Subscription> {
        val response = resource.getSkuSubscription(skuId.snowflake, subscriptionId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        skuId: SkuId,
        subscriptionId: SubscriptionId
    ): Future<Response<Subscription>> {
        return super.async(skuId, subscriptionId)
    }

    override fun block(
        skuId: SkuId,
        subscriptionId: SubscriptionId
    ): Response<Subscription> {
        return super.block(skuId, subscriptionId)
    }
}