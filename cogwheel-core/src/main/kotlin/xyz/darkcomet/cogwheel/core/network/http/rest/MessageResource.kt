package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.MessageEntity
import xyz.darkcomet.cogwheel.core.network.entities.UserEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateMessageRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.EditMessageRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getChannelMessages(
        channelId: Snowflake,
        around: Snowflake? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null
    ): CwHttpResponse<List<MessageEntity>> {
        TODO("To be implemented")
    }
    
    fun getChannelMessage(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
    fun createMessage(
        channelId: Snowflake,
        request: CreateMessageRequestEntity
    ): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
    fun crossPostMessage(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
    fun createReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun deleteOwnReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun deleteUserReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: Snowflake,
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun getReactions(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: Snowflake
    ): CwHttpResponse<List<UserEntity>> {
        TODO("To be implemented")
    }
    
    fun deleteAllReactions(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun deleteAllReactionsForEmoji(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun editMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        request: EditMessageRequestEntity
    ): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
    fun deleteMessage(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun bulkDeleteMessages(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}