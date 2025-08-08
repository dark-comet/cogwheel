@file:Suppress("unused", "RedundantOverride", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.StageInstanceResource
import xyz.darkcomet.cogwheel.framework.models.entitles.StageInstance
import xyz.darkcomet.cogwheel.framework.models.entitles.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyStageInstanceRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class StageInstanceApi
internal constructor(resource: StageInstanceResource) {
    
    @JvmField
    val create = object : RequestInvocation0S<CreateStageInstanceRequestSpec, StageInstance>() {
        
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
    
    @JvmField
    val get = object : Invocation1<ChannelId, StageInstance>() {
        
        override suspend fun invoke(p1: ChannelId): Response<StageInstance> {
            val response = resource.getStageInstance(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val modify = object : RequestInvocation1S<ChannelId, ModifyStageInstanceRequestSpec, StageInstance>() {
        
        override fun createRequest(p1: ChannelId): ModifyStageInstanceRequestSpec {
            return ModifyStageInstanceRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyStageInstanceRequestSpec): Response<StageInstance> {
            val response = resource.getStageInstance(request.channelId.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val delete = object : RequestInvocation1S<ChannelId, DeleteStageInstanceRequestSpec, Boolean>() {
        
        override fun createRequest(p1: ChannelId): DeleteStageInstanceRequestSpec {
            return DeleteStageInstanceRequestSpec(p1)
        }

        override suspend fun invoke(request: DeleteStageInstanceRequestSpec): Response<Boolean> {
            val response = resource.deleteStageInstance(
                request.channelId.snowflake,
                request.auditLogReason
            )
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}