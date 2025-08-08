@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.ChannelResource
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel
import xyz.darkcomet.cogwheel.framework.models.request.DeleteOrCloseChannelRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyDmChannelRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildChannelRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyThreadChannelRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.Response

class ChannelApi
internal constructor(resource: ChannelResource) {
    
    @JvmField
    val get = object : Invocation1<ChannelId, Channel>() {
        override suspend fun invoke(p1: ChannelId): Response<Channel> {
            val response = resource.getChannel(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }
    }

    @JvmField
    val modifyDm = object : RequestInvocation1S<ChannelId, ModifyDmChannelRequestSpec, Channel>() {
        override fun createRequest(p1: ChannelId): ModifyDmChannelRequestSpec {
            return ModifyDmChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyDmChannelRequestSpec): Response<Channel> {
            TODO()
        }

    }
    
    @JvmField
    val modifyGuild = object : RequestInvocation1S<ChannelId, ModifyGuildChannelRequestSpec, Channel>() {
        override fun createRequest(p1: ChannelId): ModifyGuildChannelRequestSpec {
            return ModifyGuildChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyGuildChannelRequestSpec): Response<Channel> {
            TODO()
        }
    }
    
    @JvmField
    val modifyThread = object : RequestInvocation1S<ChannelId, ModifyThreadChannelRequestSpec, Channel>() {
        override fun createRequest(p1: ChannelId): ModifyThreadChannelRequestSpec {
            return ModifyThreadChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyThreadChannelRequestSpec): Response<Channel> {
            TODO()
        }
    }
    
    @JvmField
    val deleteOrClose = object : RequestInvocation1S<ChannelId, DeleteOrCloseChannelRequestSpec, Channel>() {
        override fun createRequest(p1: ChannelId): DeleteOrCloseChannelRequestSpec {
            return DeleteOrCloseChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: DeleteOrCloseChannelRequestSpec): Response<Channel> {
            val response = resource.deleteOrCloseChannel(
                request.channelId.snowflake,
                request.auditLogReason
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
    }
    
}