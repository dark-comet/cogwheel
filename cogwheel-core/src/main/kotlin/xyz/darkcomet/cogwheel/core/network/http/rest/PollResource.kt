package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.GET
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.POST
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.MessageObject
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class PollResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getAnswerVoters(
        channelId: Snowflake,
        messageId: Snowflake,
        answerId: Int,
        after: Snowflake? = null,
        limit: Int? = null,
    ): CwHttpResponse<List<UserObject>> {
        val httpRequest = CwHttpRequest.create(
            GET, "/channels/${channelId}/polls/${messageId}/answers/${answerId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/polls/*/answers/*"
        ) {
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(UserObject.serializer()))
    }
    
    suspend fun endPoll(
        channelId: Snowflake, 
        messageId: Int
    ): CwHttpResponse<MessageObject> {
        
        val httpRequest = CwHttpRequest.create(
            POST, "/channels/${channelId}/polls/${messageId}/expire",
            rateLimitRouteIdentifier = "/channels/${channelId}/polls/*/expire"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
}