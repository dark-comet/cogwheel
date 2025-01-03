package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.MessageEntity
import xyz.darkcomet.cogwheel.core.network.objects.UserEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class PollResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getAnswerVoters(
        channelId: Snowflake,
        messageId: Snowflake,
        answerId: Int
    ): CwHttpResponse<List<UserEntity>> {
        TODO("To be implemented")
    }
    
    fun endPoll(channelId: Snowflake, messageId: Int): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
}