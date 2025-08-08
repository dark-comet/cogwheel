@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.VoiceResource
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.VoiceRegion
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.VoiceState
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.toModel
import xyz.darkcomet.cogwheel.framework.models.request.ModifyCurrentUserVoiceStateRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyUserVoiceStateRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class VoiceApi
internal constructor(resource: VoiceResource) {
    
    @JvmField
    val listRegions = object : Invocation0<List<VoiceRegion>>() {
        
        override suspend fun invoke(): Response<List<VoiceRegion>> {
            val response = resource.listVoiceRegions()
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val getCurrentUserVoiceState = object : Invocation1<GuildId, VoiceState>() {
        
        override suspend fun invoke(p1: GuildId): Response<VoiceState> {
            val response = resource.getCurrentUserVoiceState(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    } 
    
    @JvmField
    val getUserVoiceState = object : Invocation2<GuildId, UserId, VoiceState>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: UserId
        ): Response<VoiceState> {
            val response = resource.getUserVoiceState(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    } 
    
    @JvmField
    val modifyCurrentUserVoiceState = object : RequestInvocation1S<GuildId, ModifyCurrentUserVoiceStateRequestSpec, Boolean>() {
        
        override fun createRequest(p1: GuildId): ModifyCurrentUserVoiceStateRequestSpec {
            return ModifyCurrentUserVoiceStateRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyCurrentUserVoiceStateRequestSpec): Response<Boolean> {
            val response = resource.modifyCurrentUserVoiceState(
                request.guildId.snowflake,
                request.buildParameters()
            )
            val result = response.raw.success

            return Response(result, response)
        }

    } 
    
    @JvmField
    val modifyUserVoiceState = object : RequestInvocation2S<GuildId, UserId, ModifyUserVoiceStateRequestSpec, Boolean>() {
        override fun createRequest(
            p1: GuildId,
            p2: UserId
        ): ModifyUserVoiceStateRequestSpec {
            
            return ModifyUserVoiceStateRequestSpec(p1, p2) 
        }

        override suspend fun invoke(request: ModifyUserVoiceStateRequestSpec): Response<Boolean> {
            val response = resource.modifyUserVoiceState(
                request.guildId.snowflake,
                request.userId.snowflake,
                request.buildParameters()
            )
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}