package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EmojiResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listGuildEmojis(guildId: Snowflake): CwHttpResponse<List<EmojiObject>> {
        
        val httpRequest = CwHttpRequest.create(GET, "/emojis/${guildId}/emojis")
        val response = httpClient.submit(httpRequest)
        
        return response.withData((ListSerializer(EmojiObject.serializer())))
    }
    
    suspend fun getGuildEmoji(
        guildId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/emojis/${guildId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/emojis/${guildId}/emojis/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData((EmojiObject.serializer()))
    }
    
    suspend fun createGuildEmoji(
        guildId: Snowflake,
        request: CreateGuildEmojiRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/emojis/${guildId}/emojis") {
            jsonParams(request, CreateGuildEmojiRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData((EmojiObject.serializer()))
    }
    
    suspend fun modifyGuildEmoji(
        guildId: Snowflake,
        emojiId: Snowflake,
        request: ModifyGuildEmojiRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(
            PATCH, "/emojis/${guildId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/emojis/${guildId}/emojis/*"
        ) {
            jsonParams(request, ModifyGuildEmojiRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData((EmojiObject.serializer()))
    }
    
    suspend fun deleteGuildEmoji(
        guildId: Snowflake,
        emojiId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/emojis/${guildId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/emojis/${guildId}/emojis/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun listApplicationEmojis(applicationId: Snowflake): CwHttpResponse<ListApplicationEmojisResponseObject> {
        val httpRequest = CwHttpRequest.create(GET, "/applications/${applicationId}/emojis")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListApplicationEmojisResponseObject.serializer())
    }
    
    suspend fun getApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/applications/${applicationId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/applications/${applicationId}/emojis/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(EmojiObject.serializer())
    }
    
    suspend fun createApplicationEmoji(
        applicationId: Snowflake, 
        request: CreateApplicationEmojiRequestParameters
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/applications/${applicationId}/emojis") {
            jsonParams(request, CreateApplicationEmojiRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(EmojiObject.serializer())
    }
    
    suspend fun modifyApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake, 
        request: ModifyApplicationEmojiRequestParameters
    ): CwHttpResponse<EmojiObject> {
        
        val httpRequest = CwHttpRequest.create(
            PATCH, "/applications/${applicationId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/applications/${applicationId}/emojis/*"
        ) {
            jsonParams(request, ModifyApplicationEmojiRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(EmojiObject.serializer())
    }
    
    suspend fun deleteApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/applications/${applicationId}/emojis/${emojiId}",
            rateLimitRouteIdentifier = "/applications/${applicationId}/emojis/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
}