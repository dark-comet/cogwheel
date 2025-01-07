package xyz.darkcomet.cogwheel.core.network.http.rest

import io.ktor.client.request.forms.*
import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.StickerObject
import xyz.darkcomet.cogwheel.core.network.objects.StickerPackObject
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StickerResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getSticker(stickerId: Snowflake): CwHttpResponse<StickerObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/stickers/${stickerId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(StickerObject.serializer())
    }
    
    suspend fun listStickerPacks(): CwHttpResponse<List<StickerPackObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/sticker-packs")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(StickerPackObject.serializer()))
    }
    
    suspend fun getStickerPack(packId: Snowflake): CwHttpResponse<StickerPackObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/sticker-packs/${packId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(StickerPackObject.serializer())
    }
    
    suspend fun listGuildStickers(guildId: Snowflake): CwHttpResponse<List<StickerObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/stickers")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(StickerObject.serializer()))
    }
    
    suspend fun getGuildSticker(
        guildId: Snowflake, 
        stickerId: Snowflake
    ): CwHttpResponse<StickerObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/stickers/${stickerId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(StickerObject.serializer())
    }
    
    suspend fun createGuildSticker(
        guildId: Snowflake,
        request: CreateGuildStickerRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/stickers") {
            formData { 
                append("name", request.name)
                append("description", request.description)
                append("tags", request.tags)
                append("file", request.file)
            }
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(StickerObject.serializer())
    }
    
    suspend fun modifyGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
        request: ModifyGuildStickerRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/stickers/${stickerId}") {
            jsonParams(request, ModifyGuildStickerRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(StickerObject.serializer())
    }
    
    suspend fun deleteGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/stickers/${stickerId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}