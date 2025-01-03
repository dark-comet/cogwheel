package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.EmojiEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateApplicationEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyApplicationEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildEmojiRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.ListApplicationEmojisResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EmojiResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listGuildEmojis(guildId: Snowflake): CwHttpResponse<List<EmojiEntity>> {
        TODO("To be implemented")
    }
    
    fun getGuildEmoji(
        guildId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun createGuildEmoji(
        guildId: Snowflake,
        request: CreateGuildEmojiRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun modifyGuildEmoji(
        guildId: Snowflake,
        emojiId: Snowflake,
        request: ModifyGuildEmojiRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun listApplicationEmojis(applicationId: Snowflake): CwHttpResponse<ListApplicationEmojisResponseEntity> {
        TODO("To be implemented")
    }
    
    fun getApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun createApplicationEmoji(
        applicationId: Snowflake, 
        request: CreateApplicationEmojiRequestEntity
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun modifyApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake, 
        request: ModifyApplicationEmojiRequestEntity
    ): CwHttpResponse<EmojiEntity> {
        TODO("To be implemented")
    }
    
    fun deleteApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}