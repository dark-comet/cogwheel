package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getChannelMessages(
        channelId: Snowflake,
        around: Snowflake? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = null
    ): CwHttpResponse<List<MessageObject>> {
        
        val httpRequest = CwHttpRequest.create(GET, "/channels/${channelId}/messages") {
            optionalQueryStringParam("around", around)
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(MessageObject.serializer()))
    }
    
    suspend fun getChannelMessage(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<MessageObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/channels/${channelId}/messages/${messageId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun createMessage(
        channelId: Snowflake,
        request: CreateMessageRequestParameters
    ): CwHttpResponse<MessageObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/channels/${channelId}/messages") {
            jsonParams(request.payloadJson, CreateMessageRequestParameters.PayloadJson.serializer())
            files(request.files)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun crossPostMessage(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<MessageObject> {
        
        val httpRequest = CwHttpRequest.create(
            POST, "/channels/${channelId}/messages/${messageId}/crosspost",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/crosspost"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun createReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emoji: String
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            PUT, "/channels/${channelId}/messages/${messageId}/reactions/${emoji}/@me",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions/*/@me"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun deleteOwnReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emoji: String
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/messages/${messageId}/reactions/${emoji}/@me",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions/*/@me"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun deleteUserReaction(
        channelId: Snowflake,
        messageId: Snowflake,
        emoji: String,
        userId: Snowflake
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/messages/${messageId}/reactions/${emoji}/${userId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions/*/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getReactions(
        channelId: Snowflake,
        messageId: Snowflake,
        emoji: String,
        type: Int? = null,
        after: Snowflake? = null,
        limit: Int? = null
    ): CwHttpResponse<List<UserObject>> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/channels/${channelId}/messages/${messageId}/reactions/${emoji}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions/*"
        ) {
            optionalQueryStringParam("type", type)
            optionalQueryStringParam("after", after)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(UserObject.serializer()))
    }
    
    suspend fun deleteAllReactions(
        channelId: Snowflake,
        messageId: Snowflake
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/messages/${messageId}/reactions",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun deleteAllReactionsForEmoji(
        channelId: Snowflake,
        messageId: Snowflake,
        emoji: String
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/messages/${messageId}/reactions/${emoji}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*/reactions/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun editMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        request: EditMessageRequestParameters
    ): CwHttpResponse<MessageObject> {
        
        val httpRequest = CwHttpRequest.create(
            PATCH, "/channels/${channelId}/messages/${messageId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*"
        ) {
            jsonParams(request.payloadJson, EditMessageRequestParameters.PayloadJson.serializer())
            files(request.files)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun deleteMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/messages/${messageId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/messages/*"
        ) {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun bulkDeleteMessages(
        channelId: Snowflake,
        request: BulkDeleteMessagesRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(POST, "/channels/${channelId}/messages/bulk-delete") {
            jsonParams(request, BulkDeleteMessagesRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun getPinnedMessages(channelId: Snowflake): CwHttpResponse<List<MessageObject>> {
        val httpRequest = CwHttpRequest.create(GET, "/channels/${channelId}/pins")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(MessageObject.serializer()))
    }

    suspend fun pinMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {

        val httpRequest = CwHttpRequest.create(
            PUT, "/channels/${channelId}/pins/${messageId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/pins/*"
        ) {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun unpinMessage(
        channelId: Snowflake,
        messageId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {

        val httpRequest = CwHttpRequest.create(
            DELETE, "/channels/${channelId}/pins/${messageId}",
            rateLimitRouteIdentifier = "/channels/${channelId}/pins/*"
        ) {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
}