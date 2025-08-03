@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "unused", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.StickerResource
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.Sticker
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.StickerPack
import xyz.darkcomet.cogwheel.framework.models.entitles.sticker.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildStickerRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation0
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation2S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import xyz.darkcomet.cogwheel.framework.primitives.StickerId
import xyz.darkcomet.cogwheel.framework.primitives.StickerPackId
import java.util.concurrent.Future
import java.util.function.Consumer

class StickerApi
internal constructor(resource: StickerResource) {
    
    @JvmField
    val get = GetStickerEndpoint(resource)
    
    @JvmField
    val listPacks = ListStickerPacksEndpoint(resource)
    
    @JvmField
    val getPack = GetStickerPackEndpoint(resource)
    
    @JvmField
    val listForGuild = ListGuildStickersEndpoint(resource)
    
    @JvmField
    val getForGuild = GetGuildStickerEndpoint(resource)
    
    @JvmField
    val createForGuild = CreateGuildStickerEndpoint(resource)
    
    @JvmField
    val modifyForGuild = ModifyGuildStickerEndpoint(resource)
    
    @JvmField
    val deleteForGuild = DeleteGuildStickerEndpoint(resource)
    
}

class GetStickerEndpoint
internal constructor(private val resource: StickerResource)
    : Invocation1<StickerId, Sticker>() {
        
    override suspend fun invoke(stickerId: StickerId): Response<Sticker> {
        val response = resource.getSticker(stickerId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(stickerId: StickerId): Future<Response<Sticker>> {
        return super.async(stickerId)
    }

    override fun block(stickerId: StickerId): Response<Sticker> {
        return super.block(stickerId)
    }
}

class ListStickerPacksEndpoint
internal constructor(private val resource: StickerResource)
    : Invocation0<List<StickerPack>>() {
        
    override suspend fun invoke(): Response<List<StickerPack>> {
        val response = resource.listStickerPacks()
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }
    
}

class GetStickerPackEndpoint
internal constructor(private val resource: StickerResource)
    : Invocation1<StickerPackId, StickerPack>() {
        
    override suspend fun invoke(packId: StickerPackId): Response<StickerPack> {
        val response = resource.getStickerPack(packId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(packId: StickerPackId): Future<Response<StickerPack>> {
        return super.async(packId)
    }

    override fun block(packId: StickerPackId): Response<StickerPack> {
        return super.block(packId)
    }
}

class ListGuildStickersEndpoint
internal constructor(private val resource: StickerResource)
    : Invocation1<GuildId, List<Sticker>>() {
        
    override suspend fun invoke(guildId: GuildId): Response<List<Sticker>> {
        val response = resource.listGuildStickers(guildId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId): Future<Response<List<Sticker>>> {
        return super.async(guildId)
    }

    override fun block(guildId: GuildId): Response<List<Sticker>> {
        return super.block(guildId)
    }
}

class GetGuildStickerEndpoint
internal constructor(private val resource: StickerResource)
    : Invocation2<GuildId, StickerId, Sticker>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        stickerId: StickerId
    ): Response<Sticker> {
        val response = resource.getGuildSticker(guildId.snowflake, stickerId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        stickerId: StickerId
    ): Future<Response<Sticker>> {
        return super.async(guildId, stickerId)
    }

    override fun block(
        guildId: GuildId,
        stickerId: StickerId
    ): Response<Sticker> {
        return super.block(guildId, stickerId)
    }
}

class CreateGuildStickerEndpoint
internal constructor(private val resource: StickerResource)
    : RequestInvocation1S<GuildId, CreateGuildStickerRequestSpec, Sticker>() {
        
    override fun createRequest(guildId: GuildId): CreateGuildStickerRequestSpec {
        return CreateGuildStickerRequestSpec(guildId)
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

    override suspend fun invoke(
        guildId: GuildId,
        request: CreateGuildStickerRequestSpec?,
        config: (CreateGuildStickerRequestSpec.() -> Unit)?
    ): Response<Sticker> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<CreateGuildStickerRequestSpec>?
    ): Future<Response<Sticker>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<CreateGuildStickerRequestSpec>?
    ): Response<Sticker> {
        return super.block(guildId, config)
    }
}

class ModifyGuildStickerEndpoint
internal constructor(private val resource: StickerResource)
    : RequestInvocation2S<GuildId, StickerId, ModifyGuildStickerRequestSpec, Sticker>() {

    override fun createRequest(
        guildId: GuildId,
        stickerId: StickerId
    ): ModifyGuildStickerRequestSpec {
        return ModifyGuildStickerRequestSpec(guildId, stickerId)
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

    override suspend fun invoke(
        guildId: GuildId,
        stickerId: StickerId,
        request: ModifyGuildStickerRequestSpec?,
        config: (ModifyGuildStickerRequestSpec.() -> Unit)?
    ): Response<Sticker> {
        return super.invoke(guildId, stickerId, request, config)
    }

    override fun async(
        guildId: GuildId,
        stickerId: StickerId,
        config: Consumer<ModifyGuildStickerRequestSpec>?
    ): Future<Response<Sticker>> {
        return super.async(guildId, stickerId, config)
    }

    override fun block(
        guildId: GuildId,
        stickerId: StickerId,
        config: Consumer<ModifyGuildStickerRequestSpec>?
    ): Response<Sticker> {
        return super.block(guildId, stickerId, config)
    }
}

class DeleteGuildStickerEndpoint
internal constructor(private val resource: StickerResource)
    : RequestInvocation2S<GuildId, StickerId, DeleteGuildStickerRequestSpec, Boolean>() {
        
    override fun createRequest(
        guildId: GuildId,
        stickerId: StickerId
    ): DeleteGuildStickerRequestSpec {
        return DeleteGuildStickerRequestSpec(guildId, stickerId)
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

    override suspend fun invoke(
        guildId: GuildId,
        stickerId: StickerId,
        request: DeleteGuildStickerRequestSpec?,
        config: (DeleteGuildStickerRequestSpec.() -> Unit)?
    ): Response<Boolean> {
        return super.invoke(guildId, stickerId, request, config)
    }

    override fun async(
        guildId: GuildId,
        stickerId: StickerId,
        config: Consumer<DeleteGuildStickerRequestSpec>?
    ): Future<Response<Boolean>> {
        return super.async(guildId, stickerId, config)
    }

    override fun block(
        guildId: GuildId,
        stickerId: StickerId,
        config: Consumer<DeleteGuildStickerRequestSpec>?
    ): Response<Boolean> {
        return super.block(guildId, stickerId, config)
    }
}