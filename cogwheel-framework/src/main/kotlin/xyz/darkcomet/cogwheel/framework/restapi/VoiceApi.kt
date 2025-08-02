@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.VoiceResource
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.VoiceRegion
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.VoiceState
import xyz.darkcomet.cogwheel.framework.models.entitles.voice.toModel
import xyz.darkcomet.cogwheel.framework.models.request.ModifyCurrentUserVoiceStateRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyUserVoiceStateRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation0
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation2S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import java.util.concurrent.Future
import java.util.function.Consumer

class VoiceApi
internal constructor(resource: VoiceResource) {
    
    @JvmField
    val listRegions = ListVoiceRegionsEndpoint(resource)
    
    @JvmField
    val getCurrentUserVoiceState = GetCurrentUserVoiceStateEndpoint(resource)
    
    @JvmField
    val getUserVoiceState = GetUserVoiceStateEndpoint(resource)
    
    @JvmField
    val modifyCurrentUserVoiceState = ModifyCurrentUserVoiceStateEndpoint(resource)
    
    @JvmField
    val modifyUserVoiceState = ModifyUserVoiceStateEndpoint(resource)
    
}

class ListVoiceRegionsEndpoint
internal constructor(private val resource: VoiceResource) 
    : Invocation0<List<VoiceRegion>>() {
        
    override suspend fun invoke(): Response<List<VoiceRegion>> {
        val response = resource.listVoiceRegions()
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

}

class GetCurrentUserVoiceStateEndpoint
internal constructor(private val resource: VoiceResource)
    : Invocation1<GuildId, VoiceState>() {
        
    override suspend fun invoke(guildId: GuildId): Response<VoiceState> {
        val response = resource.getCurrentUserVoiceState(guildId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId): Future<Response<VoiceState>> {
        return super.async(guildId)
    }

    override fun block(guildId: GuildId): Response<VoiceState> {
        return super.block(guildId)
    }
}

class GetUserVoiceStateEndpoint
internal constructor(private val resource: VoiceResource)
    : Invocation2<GuildId, UserId, VoiceState>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        userId: UserId
    ): Response<VoiceState> {
        val response = resource.getUserVoiceState(guildId.snowflake, userId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        userId: UserId
    ): Future<Response<VoiceState>> {
        return super.async(guildId, userId)
    }

    override fun block(
        guildId: GuildId,
        userId: UserId
    ): Response<VoiceState> {
        return super.block(guildId, userId)
    }
}

class ModifyCurrentUserVoiceStateEndpoint
internal constructor(private val resource: VoiceResource)
    : RequestInvocation1S<GuildId, ModifyCurrentUserVoiceStateRequestSpec, Boolean>() {
        
    override fun createRequest(guildId: GuildId): ModifyCurrentUserVoiceStateRequestSpec {
        return ModifyCurrentUserVoiceStateRequestSpec(guildId)
    }

    override suspend fun invoke(request: ModifyCurrentUserVoiceStateRequestSpec): Response<Boolean> {
        val response = resource.modifyCurrentUserVoiceState(
            request.guildId.snowflake,
            request.buildParameters()
        )
        val result = response.raw.success
        
        return Response(result, response)
    }

    override suspend fun invoke(
        guildId: GuildId,
        request: ModifyCurrentUserVoiceStateRequestSpec?,
        config: (ModifyCurrentUserVoiceStateRequestSpec.() -> Unit)?
    ): Response<Boolean> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<ModifyCurrentUserVoiceStateRequestSpec>?
    ): Future<Response<Boolean>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<ModifyCurrentUserVoiceStateRequestSpec>?
    ): Response<Boolean> {
        return super.block(guildId, config)
    }
}

class ModifyUserVoiceStateEndpoint
internal constructor(private val resource: VoiceResource)
    : RequestInvocation2S<GuildId, UserId, ModifyUserVoiceStateRequestSpec, Boolean>() {
        
    override fun createRequest(
        guildId: GuildId,
        userId: UserId
    ): ModifyUserVoiceStateRequestSpec {
        return ModifyUserVoiceStateRequestSpec(guildId, userId)
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

    override suspend fun invoke(
        guildId: GuildId,
        userId: UserId,
        request: ModifyUserVoiceStateRequestSpec?,
        config: (ModifyUserVoiceStateRequestSpec.() -> Unit)?
    ): Response<Boolean> {
        return super.invoke(guildId, userId, request, config)
    }

    override fun async(
        guildId: GuildId,
        userId: UserId,
        config: Consumer<ModifyUserVoiceStateRequestSpec>?
    ): Future<Response<Boolean>> {
        return super.async(guildId, userId, config)
    }

    override fun block(
        guildId: GuildId,
        userId: UserId,
        config: Consumer<ModifyUserVoiceStateRequestSpec>?
    ): Response<Boolean> {
        return super.block(guildId, userId, config)
    }
}