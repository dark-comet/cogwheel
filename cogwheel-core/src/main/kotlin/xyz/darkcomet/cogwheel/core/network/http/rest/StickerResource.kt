package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.StickerEntity
import xyz.darkcomet.cogwheel.core.network.entities.StickerPackEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateGuildStickerRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyGuildStickerRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StickerResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getSticker(stickerId: Snowflake): CwHttpResponse<StickerEntity> {
        TODO("To be implemented")
    }
    
    fun listStickerPacks(): CwHttpResponse<List<StickerPackEntity>> {
        TODO("To be implemented")
    }
    
    fun getStickerPack(packId: Snowflake): CwHttpResponse<StickerPackEntity> {
        TODO("To be implemented")
    }
    
    fun listGuildStickers(guildId: Snowflake): CwHttpResponse<List<StickerResource>> {
        TODO("To be implemented")
    }
    
    fun getGuildSticker(
        guildId: Snowflake, 
        stickerId: Snowflake
    ): CwHttpResponse<StickerEntity> {
        TODO("To be implemented")
    }
    
    fun createGuildSticker(
        guildId: Snowflake, 
        request: CreateGuildStickerRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerEntity> {
        TODO("To be implemented")
    }
    
    fun modifyGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
        request: ModifyGuildStickerRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<StickerEntity> {
        TODO("To be implemented")
    }
    
    fun deleteGuildSticker(
        guildId: Snowflake,
        stickerId: Snowflake,
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}