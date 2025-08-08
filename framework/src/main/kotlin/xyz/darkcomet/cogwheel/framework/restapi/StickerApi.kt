@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.StickerResource
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.Sticker
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.StickerPack
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class StickerApi
internal constructor(resource: StickerResource) {
    
    @JvmField
    val get = object : Invocation1<StickerId, Sticker>() {
        
        override suspend fun invoke(p1: StickerId): Response<Sticker> {
            val response = resource.getSticker(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val listPacks = object : Invocation0<List<StickerPack>>() {
        
        override suspend fun invoke(): Response<List<StickerPack>> {
            val response = resource.listStickerPacks()
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val getPack = object : Invocation1<StickerPackId, StickerPack>() {
        
        override suspend fun invoke(p1: StickerPackId): Response<StickerPack> {
            val response = resource.getStickerPack(p1.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    } 
    
    @JvmField
    val listForGuild = object : Invocation1<GuildId, List<Sticker>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<Sticker>> {
            val response = resource.listGuildStickers(p1.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    
    @JvmField
    val getForGuild = object : Invocation2<GuildId, StickerId, Sticker>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: StickerId
        ): Response<Sticker> {
            val response = resource.getGuildSticker(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val createForGuild = object : RequestInvocation1S<GuildId, CreateGuildStickerRequestSpec, Sticker>() {
        
        override fun createRequest(p1: GuildId): CreateGuildStickerRequestSpec {
            return CreateGuildStickerRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateGuildStickerRequestSpec): Response<Sticker> {
            val response = resource.createGuildSticker(
                request.guildId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val modifyForGuild = object : RequestInvocation2S<GuildId, StickerId, ModifyGuildStickerRequestSpec, Sticker>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: StickerId
        ): ModifyGuildStickerRequestSpec {

            return ModifyGuildStickerRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyGuildStickerRequestSpec): Response<Sticker> {
            val response = resource.modifyGuildSticker(
                request.guildId.snowflake,
                request.stickerId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val deleteForGuild = object : RequestInvocation2S<GuildId, StickerId, DeleteGuildStickerRequestSpec, Boolean>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: StickerId
        ): DeleteGuildStickerRequestSpec {
            
            return DeleteGuildStickerRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: DeleteGuildStickerRequestSpec): Response<Boolean> {
            val response = resource.deleteGuildSticker(
                request.guildId.snowflake,
                request.stickerId.snowflake,
                request.auditLogReason
            )
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}