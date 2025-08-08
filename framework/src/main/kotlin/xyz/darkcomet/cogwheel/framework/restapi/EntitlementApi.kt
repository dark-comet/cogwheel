@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.EntitlementResource
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.Entitlement
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.PartialEntitlement
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.toPartialModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateTestEntitlementRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ListEntitlementsRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class EntitlementApi
internal constructor(resource: EntitlementResource) {
    
    @JvmField
    val list = object : RequestInvocation1S<ApplicationId, ListEntitlementsRequestSpec, List<Entitlement>>() {
        
        override fun createRequest(p1: ApplicationId): ListEntitlementsRequestSpec {
            return ListEntitlementsRequestSpec(p1)
        }

        override suspend fun invoke(request: ListEntitlementsRequestSpec): Response<List<Entitlement>> {
            val response = resource.listEntitlements(
                request.applicationId.snowflake,
                request.userId,
                request.skuIds,
                request.before,
                request.after,
                request.limit,
                request.guildId,
                request.excludeEnded,
                request.excludeDeleted
            )
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val get = object : Invocation2<ApplicationId, EntitlementId, Entitlement>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: EntitlementId
        ): Response<Entitlement> {
            val response = resource.getEntitlement(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val consume = object : Invocation2<ApplicationId, EntitlementId, Boolean>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: EntitlementId
        ): Response<Boolean> {
            val response = resource.consumeEntitlement(p1.snowflake, p2.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
    @JvmField
    val createTest = object : RequestInvocation1S<ApplicationId, CreateTestEntitlementRequestSpec, PartialEntitlement>() {
        
        override fun createRequest(p1: ApplicationId): CreateTestEntitlementRequestSpec {
            return CreateTestEntitlementRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateTestEntitlementRequestSpec): Response<PartialEntitlement> {
            val response = resource.createTestEntitlement(
                request.applicationId.snowflake,
                request.buildParameters()
            )
            val result = response.data?.toPartialModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val deleteTest = object : Invocation2<ApplicationId, EntitlementId, Boolean>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: EntitlementId
        ): Response<Boolean> {
            val response = resource.deleteTestEntitlement(p1.snowflake, p2.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}