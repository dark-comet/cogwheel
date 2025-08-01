@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.SoundboardResource
import xyz.darkcomet.cogwheel.framework.models.entitles.SoundboardSound
import xyz.darkcomet.cogwheel.framework.models.entitles.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.SendSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.concurrent.Future
import java.util.function.Consumer

class SoundboardApi
internal constructor(resource: SoundboardResource) {
    
    @JvmField
    val send = SendSoundboardSoundEndpoint(resource)
    
    @JvmField
    val listDefault = ListDefaultSoundboardSoundsEndpoint(resource)
    
    @JvmField
    val listForGuild = ListGuildSoundboardSoundsEndpoint(resource)
    
    @JvmField
    val getForGuild = GetGuildSoundboardSoundEndpoint(resource)
    
    @JvmField
    val createForGuild = CreateGuildSoundboardSoundEndpoint(resource)
    
    @JvmField
    val modifyForGuild = ModifyGuildSoundboardSoundEndpoint(resource)
    
    @JvmField
    val deleteForGuild = DeleteGuildSoundboardSoundEndpoint(resource)
    
}

class SendSoundboardSoundEndpoint
internal constructor(private val resource: SoundboardResource) 
    : RequestInvocation1S<ChannelId, SendSoundboardSoundRequestSpec, Boolean>() {
        
    override fun createRequest(channelId: ChannelId): SendSoundboardSoundRequestSpec {
        return SendSoundboardSoundRequestSpec(channelId)
    }

    override suspend fun invoke(request: SendSoundboardSoundRequestSpec): Response<Boolean> {
        val response = resource.sendSoundboardSound(
            request.channelId.snowflake,
            request.buildParameters()
        )
        val result = response.raw.success
        
        return Response(result, response)
    }

    override suspend fun invoke(
        channelId: ChannelId,
        request: SendSoundboardSoundRequestSpec?,
        config: (SendSoundboardSoundRequestSpec.() -> Unit)?
    ): Response<Boolean> {
        return super.invoke(channelId, request, config)
    }

    override fun async(
        channelId: ChannelId,
        config: Consumer<SendSoundboardSoundRequestSpec>?
    ): Future<Response<Boolean>> {
        return super.async(channelId, config)
    }

    override fun block(
        channelId: ChannelId,
        config: Consumer<SendSoundboardSoundRequestSpec>?
    ): Response<Boolean> {
        return super.block(channelId, config)
    }
}

class ListDefaultSoundboardSoundsEndpoint
internal constructor(private val resource: SoundboardResource) 
    : Invocation0<List<SoundboardSound>>() {
        
    override suspend fun invoke(): Response<List<SoundboardSound>> {
        val response = resource.listDefaultSoundboardSounds()
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }
    
}

class ListGuildSoundboardSoundsEndpoint
internal constructor(private val resource: SoundboardResource)
    : Invocation1<GuildId, List<SoundboardSound>>() {
        
    override suspend fun invoke(guildId: GuildId): Response<List<SoundboardSound>> {
        val response = resource.listGuildSoundboardSounds(guildId.snowflake)
        val result = response.data?.items?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId): Future<Response<List<SoundboardSound>>> {
        return super.async(guildId)
    }

    override fun block(guildId: GuildId): Response<List<SoundboardSound>> {
        return super.block(guildId)
    }
}

class GetGuildSoundboardSoundEndpoint
internal constructor(private val resource: SoundboardResource)
    : Invocation2<GuildId, SoundboardSoundId, SoundboardSound>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Response<SoundboardSound> {
        val response = resource.getGuildSoundboardSound(guildId.snowflake, soundId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Future<Response<SoundboardSound>> {
        return super.async(guildId, soundId)
    }

    override fun block(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Response<SoundboardSound> {
        return super.block(guildId, soundId)
    }
    
}

class CreateGuildSoundboardSoundEndpoint
internal constructor(private val resource: SoundboardResource)
    : RequestInvocation1S<GuildId, CreateGuildSoundboardSoundRequestSpec, SoundboardSound>() {
        
    override fun createRequest(guildId: GuildId): CreateGuildSoundboardSoundRequestSpec {
        return CreateGuildSoundboardSoundRequestSpec(guildId)
    }

    override suspend fun invoke(request: CreateGuildSoundboardSoundRequestSpec): Response<SoundboardSound> {
        val response = resource.createGuildSoundboardSound(
            request.guildId.snowflake,
            request.buildParameters(),
            request.auditLogReason
        )
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<CreateGuildSoundboardSoundRequestSpec>?
    ): Future<Response<SoundboardSound>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<CreateGuildSoundboardSoundRequestSpec>?
    ): Response<SoundboardSound> {
        return super.block(guildId, config)
    }
}

class ModifyGuildSoundboardSoundEndpoint
internal constructor(private val resource: SoundboardResource)
    : RequestInvocation2S<GuildId, SoundboardSoundId, ModifyGuildSoundboardSoundRequestSpec, SoundboardSound>() {
        
    override fun createRequest(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): ModifyGuildSoundboardSoundRequestSpec {
        return ModifyGuildSoundboardSoundRequestSpec(guildId, soundId)
    }

    override suspend fun invoke(request: ModifyGuildSoundboardSoundRequestSpec): Response<SoundboardSound> {
        val response = resource.modifyGuildSoundboardSound(
            request.guildId.snowflake,
            request.soundId.snowflake,
            request.buildParameters(),
            request.auditLogReason
        )
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        soundId: SoundboardSoundId,
        config: Consumer<ModifyGuildSoundboardSoundRequestSpec>?
    ): Future<Response<SoundboardSound>> {
        return super.async(guildId, soundId, config)
    }

    override fun block(
        guildId: GuildId,
        soundId: SoundboardSoundId,
        config: Consumer<ModifyGuildSoundboardSoundRequestSpec>?
    ): Response<SoundboardSound> {
        return super.block(guildId, soundId, config)
    }
}

class DeleteGuildSoundboardSoundEndpoint
internal constructor(private val resource: SoundboardResource)
    : Invocation2<GuildId, SoundboardSoundId, Boolean>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Response<Boolean> {
        val response = resource.deleteGuildSoundboardSound(guildId.snowflake, soundId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Future<Response<Boolean>> {
        return super.async(guildId, soundId)
    }

    override fun block(
        guildId: GuildId,
        soundId: SoundboardSoundId
    ): Response<Boolean> {
        return super.block(guildId, soundId)
    }
}