package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.EmojiEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateApplicationEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateGuildEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyApplicationEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyGuildEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.response.ListApplicationEmojisResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EmojiResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listForGuild(guildId: Snowflake): CwHttpResponse<List<EmojiEntity>> {
        TODO("To be implemented")
    }
    
    fun getForGuild(guildId: Snowflake, emojiId: Snowflake): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun createForGuild(
        guildId: Snowflake,
        request: CreateGuildEmojiRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun modifyForGuild(
        guildId: Snowflake,
        emojiId: Snowflake,
        request: ModifyGuildEmojiRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun listForApplication(applicationId: Snowflake): CwHttpResponse<ListApplicationEmojisResponseEntity> {
        TODO("To be implemented")
    }
    
    fun getForApplication(applicationId: Snowflake, emojiId: Snowflake): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun createForApplication(
        applicationId: Snowflake, 
        request: CreateApplicationEmojiRequestEntity
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun modifyForApplication(
        applicationId: Snowflake, 
        emojiId: Snowflake, 
        request: ModifyApplicationEmojiRequestEntity
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun deleteForApplication(applicationId: Snowflake, emojiId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}