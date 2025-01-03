package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.response.ListApplicationEmojisResponseObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EmojiResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listGuildEmojis(guildId: Snowflake): CwHttpResponse<List<EmojiObject>> {
        TODO("To be implemented")
    }
    
    fun getGuildEmoji(
        guildId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun createGuildEmoji(
        guildId: Snowflake,
        request: CreateGuildEmojiRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun modifyGuildEmoji(
        guildId: Snowflake,
        emojiId: Snowflake,
        request: ModifyGuildEmojiRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun listApplicationEmojis(applicationId: Snowflake): CwHttpResponse<ListApplicationEmojisResponseObject> {
        TODO("To be implemented")
    }
    
    fun getApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun createApplicationEmoji(
        applicationId: Snowflake, 
        request: CreateApplicationEmojiRequestParameters
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun modifyApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake, 
        request: ModifyApplicationEmojiRequestParameters
    ): CwHttpResponse<EmojiObject> {
        TODO("To be implemented")
    }
    
    fun deleteApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}