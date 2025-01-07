package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.network.objects.CreateApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ListApplicationEmojisResponseObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class EmojiResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listGuildEmojis(guildId: Snowflake): CwHttpResponse<List<EmojiObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/emojis/${guildId}/emojis")
        val response = httpClient.submit(httpRequest)
        
        return response.withData((ListSerializer(EmojiObject.serializer())))
    }
    
    suspend fun getGuildEmoji(
        guildId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/emojis/${guildId}/emojis/${emojiId}")
        val response = httpClient.submit(httpRequest)

        return response.withData((EmojiObject.serializer()))
    }
    
    suspend fun createGuildEmoji(
        guildId: Snowflake,
        request: CreateGuildEmojiRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<EmojiObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/emojis/${guildId}/emojis") {
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
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/emojis/${guildId}/emojis/${emojiId}") {
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
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/emojis/${guildId}/emojis/${emojiId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun listApplicationEmojis(applicationId: Snowflake): CwHttpResponse<ListApplicationEmojisResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/applications/${applicationId}/emojis")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListApplicationEmojisResponseObject.serializer())
    }
    
    suspend fun getApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<EmojiObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/applications/${applicationId}/emojis/${emojiId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(EmojiObject.serializer())
    }
    
    suspend fun createApplicationEmoji(
        applicationId: Snowflake, 
        request: CreateApplicationEmojiRequestParameters
    ): CwHttpResponse<EmojiObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/applications/${applicationId}/emojis") {
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
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/applications/${applicationId}/emojis/${emojiId}") {
            jsonParams(request, ModifyApplicationEmojiRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(EmojiObject.serializer())
    }
    
    suspend fun deleteApplicationEmoji(
        applicationId: Snowflake, 
        emojiId: Snowflake
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/applications/${applicationId}/emojis/${emojiId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
}