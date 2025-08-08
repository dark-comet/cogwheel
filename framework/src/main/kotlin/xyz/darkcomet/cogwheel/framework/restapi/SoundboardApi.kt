@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.SoundboardResource
import xyz.darkcomet.cogwheel.framework.models.entitles.SoundboardSound
import xyz.darkcomet.cogwheel.framework.models.entitles.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.SendSoundboardSoundRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class SoundboardApi
internal constructor(resource: SoundboardResource) {
    
    @JvmField
    val send = object : RequestInvocation1S<ChannelId, SendSoundboardSoundRequestSpec, Boolean>() {
        
        override fun createRequest(p1: ChannelId): SendSoundboardSoundRequestSpec {
            return SendSoundboardSoundRequestSpec(p1)
        }

        override suspend fun invoke(request: SendSoundboardSoundRequestSpec): Response<Boolean> {
            val response = resource.sendSoundboardSound(
                request.channelId.snowflake,
                request.buildParameters()
            )
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
    @JvmField
    val listDefault = object : Invocation0<List<SoundboardSound>>() {
        
        override suspend fun invoke(): Response<List<SoundboardSound>> {
            val response = resource.listDefaultSoundboardSounds()
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val listForGuild = object : Invocation1<GuildId, List<SoundboardSound>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<SoundboardSound>> {
            val response = resource.listGuildSoundboardSounds(p1.snowflake)
            val result = response.data?.items?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val getForGuild = object : Invocation2<GuildId, SoundboardSoundId, SoundboardSound>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: SoundboardSoundId
        ): Response<SoundboardSound> {
            val response = resource.getGuildSoundboardSound(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    } 
    
    @JvmField
    val createForGuild = object : RequestInvocation1S<GuildId, CreateGuildSoundboardSoundRequestSpec, SoundboardSound>() {
        
        override fun createRequest(p1: GuildId): CreateGuildSoundboardSoundRequestSpec {
            return CreateGuildSoundboardSoundRequestSpec(p1)
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

    } 
    
    @JvmField
    val modifyForGuild = object : RequestInvocation2S<GuildId, SoundboardSoundId, ModifyGuildSoundboardSoundRequestSpec, SoundboardSound>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: SoundboardSoundId
        ): ModifyGuildSoundboardSoundRequestSpec {
            return ModifyGuildSoundboardSoundRequestSpec(p1, p2)
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
        
    }
    
    @JvmField
    val deleteForGuild = object : Invocation2<GuildId, SoundboardSoundId, Boolean>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: SoundboardSoundId
        ): Response<Boolean> {
            val response = resource.deleteGuildSoundboardSound(p1.snowflake, p2.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }
        
    }
    
}