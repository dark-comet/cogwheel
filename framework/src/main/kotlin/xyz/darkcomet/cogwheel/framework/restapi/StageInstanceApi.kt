@file:Suppress("unused", "RedundantOverride", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.StageInstanceResource
import xyz.darkcomet.cogwheel.framework.models.entitles.StageInstance
import xyz.darkcomet.cogwheel.framework.models.entitles.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocationS
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future
import java.util.function.Consumer

class StageInstanceApi
internal constructor(resource: StageInstanceResource) {
    
    @JvmField
    val create = CreateStageInstanceEndpoint(resource)
    
    @JvmField
    val get = GetStageInstanceEndpoint(resource)
    
    @JvmField
    val modify = ModifyStageInstanceEndpoint(resource)
    
    @JvmField
    val delete = DeleteStageInstanceEndpoint(resource)
    
}

class CreateStageInstanceEndpoint
internal constructor(private val resource: StageInstanceResource) 
    : RequestInvocationS<CreateStageInstanceRequestSpec, StageInstance>() {
        
    override fun createRequest(): CreateStageInstanceRequestSpec {
        return CreateStageInstanceRequestSpec()
    }

    override suspend fun invoke(request: CreateStageInstanceRequestSpec): Response<StageInstance> {
        val response = resource.createStageInstance(
            request.buildParameters(), 
            request.auditLogReason
        )
        val result = response.data?.toModel()
        
        return Response(result, response)
    }
    
}

class GetStageInstanceEndpoint
internal constructor(private val resource: StageInstanceResource)
    : Invocation1<ChannelId, StageInstance>() {
        
    override suspend fun invoke(channelId: ChannelId): Response<StageInstance> {
        val response = resource.getStageInstance(channelId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(channelId: ChannelId): Future<Response<StageInstance>> {
        return super.async(channelId)
    }

    override fun block(channelId: ChannelId): Response<StageInstance> {
        return super.block(channelId)
    }
}

class ModifyStageInstanceEndpoint
internal constructor(private val resource: StageInstanceResource)
    : RequestInvocation1S<ChannelId, ModifyStageInstanceRequestSpec, StageInstance>() {
        
    override fun createRequest(channelId: ChannelId): ModifyStageInstanceRequestSpec {
        return ModifyStageInstanceRequestSpec(channelId)
    }

    override suspend fun invoke(request: ModifyStageInstanceRequestSpec): Response<StageInstance> {
        val response = resource.getStageInstance(request.channelId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override suspend fun invoke(
        channelId: ChannelId,
        request: ModifyStageInstanceRequestSpec?,
        config: (ModifyStageInstanceRequestSpec.() -> Unit)?
    ): Response<StageInstance> {
        return super.invoke(channelId, request, config)
    }

    override fun async(
        channelId: ChannelId,
        config: Consumer<ModifyStageInstanceRequestSpec>?
    ): Future<Response<StageInstance>> {
        return super.async(channelId, config)
    }

    override fun block(
        channelId: ChannelId,
        config: Consumer<ModifyStageInstanceRequestSpec>?
    ): Response<StageInstance> {
        return super.block(channelId, config)
    }
}

class DeleteStageInstanceEndpoint
internal constructor(private val resource: StageInstanceResource)
    : RequestInvocation1S<ChannelId, DeleteStageInstanceRequestSpec, Boolean>() {
        
    override fun createRequest(channelId: ChannelId): DeleteStageInstanceRequestSpec {
        return DeleteStageInstanceRequestSpec(channelId)
    }

    override suspend fun invoke(request: DeleteStageInstanceRequestSpec): Response<Boolean> {
        val response = resource.deleteStageInstance(
            request.channelId.snowflake, 
            request.auditLogReason
        )
        val result = response.raw.success
        
        return Response(result, response)
    }

    override suspend fun invoke(
        channelId: ChannelId,
        request: DeleteStageInstanceRequestSpec?,
        config: (DeleteStageInstanceRequestSpec.() -> Unit)?
    ): Response<Boolean> {
        return super.invoke(channelId, request, config)
    }

    override fun async(
        channelId: ChannelId,
        config: Consumer<DeleteStageInstanceRequestSpec>?
    ): Future<Response<Boolean>> {
        return super.async(channelId, config)
    }

    override fun block(
        channelId: ChannelId,
        config: Consumer<DeleteStageInstanceRequestSpec>?
    ): Response<Boolean> {
        return super.block(channelId, config)
    }
}