package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.StickerObject
import xyz.darkcomet.cogwheel.core.network.objects.StickerPackObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildStickerRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StickerResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getSticker(stickerId: Snowflake): CwHttpResponse<StickerObject> {
        TODO("To be implemented")
    }
    
    fun listStickerPacks(): CwHttpResponse<List<StickerPackObject>> {
        TODO("To be implemented")
    }
    
    fun getStickerPack(packId: Snowflake): CwHttpResponse<StickerPackObject> {
        TODO("To be implemented")
    }
    
    fun listGuildStickers(guildId: Snowflake): CwHttpResponse<List<StickerResource>> {
        TODO("To be implemented")
    }
    
    fun getGuildSticker(
        guildId: Snowflake, 
        stickerId: Snowflake
    ): CwHttpResponse<StickerObject> {
        TODO("To be implemented")
    }
    
    fun createGuildSticker(
        guildId: Snowflake,
        request: CreateGuildStickerRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerObject> {
        TODO("To be implemented")
    }
    
    fun modifyGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
        request: ModifyGuildStickerRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerObject> {
        TODO("To be implemented")
    }
    
    fun deleteGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}