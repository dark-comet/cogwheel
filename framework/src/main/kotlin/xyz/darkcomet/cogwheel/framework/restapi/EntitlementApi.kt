@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.EntitlementResource
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.Entitlement
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.PartialEntitlement
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.entitlement.toPartialModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateTestEntitlementRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ListEntitlementsRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future
import java.util.function.Consumer

class EntitlementApi
internal constructor(resource: EntitlementResource) {
    
    @JvmField
    val list = ListEntitlementsEndpoint(resource)
    
    @JvmField
    val get = GetEntitlementEndpoint(resource)
    
    @JvmField
    val consume = ConsumeEntitlementEndpoint(resource)
    
    @JvmField
    val createTest = CreateTestEntitlementEndpoint(resource)
    
    @JvmField
    val deleteTest = DeleteTestEntitlementEndpoint(resource)
    
}

class ListEntitlementsEndpoint
internal constructor(private val resource: EntitlementResource) 
    : RequestInvocation1S<ApplicationId, ListEntitlementsRequestSpec, List<Entitlement>>() {
    
    override fun createRequest(applicationId: ApplicationId): ListEntitlementsRequestSpec {
        return ListEntitlementsRequestSpec(applicationId)
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

    override suspend fun invoke(
        applicationId: ApplicationId,
        request: ListEntitlementsRequestSpec?,
        config: (ListEntitlementsRequestSpec.() -> Unit)?
    ): Response<List<Entitlement>> {
        return super.invoke(applicationId, request, config)
    }

    override fun async(
        applicationId: ApplicationId,
        config: Consumer<ListEntitlementsRequestSpec>?
    ): Future<Response<List<Entitlement>>> {
        return super.async(applicationId, config)
    }

    override fun block(
        applicationId: ApplicationId,
        config: Consumer<ListEntitlementsRequestSpec>?
    ): Response<List<Entitlement>> {
        return super.block(applicationId, config)
    }
}

class GetEntitlementEndpoint
internal constructor(private val resource: EntitlementResource)
    : Invocation2<ApplicationId, EntitlementId, Entitlement>() {
    
    override suspend fun invoke(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Entitlement> {
        val response = resource.getEntitlement(applicationId.snowflake, entitlementId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Future<Response<Entitlement>> {
        return super.async(applicationId, entitlementId)
    }

    override fun block(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Entitlement> {
        return super.block(applicationId, entitlementId)
    }
}

class ConsumeEntitlementEndpoint
internal constructor(private val resource: EntitlementResource)
    : Invocation2<ApplicationId, EntitlementId, Boolean>() {
        
    override suspend fun invoke(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Boolean> {
        val response = resource.consumeEntitlement(applicationId.snowflake, entitlementId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Future<Response<Boolean>> {
        return super.async(applicationId, entitlementId)
    }

    override fun block(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Boolean> {
        return super.block(applicationId, entitlementId)
    }
}

class CreateTestEntitlementEndpoint
internal constructor(private val resource: EntitlementResource)
    : RequestInvocation1S<ApplicationId, CreateTestEntitlementRequestSpec, PartialEntitlement>() {
        
    override fun createRequest(applicationId: ApplicationId): CreateTestEntitlementRequestSpec {
        return CreateTestEntitlementRequestSpec(applicationId)
    }

    override suspend fun invoke(request: CreateTestEntitlementRequestSpec): Response<PartialEntitlement> {
        val response = resource.createTestEntitlement(
            request.applicationId.snowflake, 
            request.buildParameters()
        )
        val result = response.data?.toPartialModel()
        
        return Response(result, response)
    }

    override suspend fun invoke(
        applicationId: ApplicationId,
        request: CreateTestEntitlementRequestSpec?,
        config: (CreateTestEntitlementRequestSpec.() -> Unit)?
    ): Response<PartialEntitlement> {
        return super.invoke(applicationId, request, config)
    }

    override fun async(
        applicationId: ApplicationId,
        config: Consumer<CreateTestEntitlementRequestSpec>?
    ): Future<Response<PartialEntitlement>> {
        return super.async(applicationId, config)
    }

    override fun block(
        applicationId: ApplicationId,
        config: Consumer<CreateTestEntitlementRequestSpec>?
    ): Response<PartialEntitlement> {
        return super.block(applicationId, config)
    }
}

class DeleteTestEntitlementEndpoint
internal constructor(private val resource: EntitlementResource)
    : Invocation2<ApplicationId, EntitlementId, Boolean>() {
        
    override suspend fun invoke(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Boolean> {
        val response = resource.deleteTestEntitlement(applicationId.snowflake, entitlementId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Future<Response<Boolean>> {
        return super.async(applicationId, entitlementId)
    }

    override fun block(
        applicationId: ApplicationId,
        entitlementId: EntitlementId
    ): Response<Boolean> {
        return super.block(applicationId, entitlementId)
    }
}