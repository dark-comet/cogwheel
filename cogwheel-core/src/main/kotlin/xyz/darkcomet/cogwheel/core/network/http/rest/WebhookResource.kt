package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.objects.MessageObject
import xyz.darkcomet.cogwheel.core.network.objects.WebhookObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.CreateWebhookRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.EditWebhookMessageRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ExecuteWebhookRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyWebhookRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class WebhookResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun createWebhook(
        channelId: Snowflake,
        request: CreateWebhookRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/webhooks") {
            jsonParams(request, CreateWebhookRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(WebhookObject.serializer())
    }
    
    suspend fun getChannelWebhooks(channelId: Snowflake): CwHttpResponse<List<WebhookObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/channels/${channelId}/webhooks")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(WebhookObject.serializer()))
    }
    
    suspend fun getGuildWebhooks(guildId: Snowflake): CwHttpResponse<List<WebhookObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/webhooks")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(WebhookObject.serializer()))
    }
    
    suspend fun getWebhook(webhookId: Snowflake): CwHttpResponse<WebhookObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/webhooks/${webhookId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(WebhookObject.serializer())
    }
    
    suspend fun getWebhookWithToken(
        webhookId: Snowflake, 
        webhookToken: String
    ): CwHttpResponse<WebhookObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/webhooks/${webhookId}/${webhookToken}")
        val response = httpClient.submit(httpRequest)

        return response.withData(WebhookObject.serializer())
    }
    
    suspend fun modifyWebhook(
        webhookId: Snowflake,
        request: ModifyWebhookRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/webhooks/${webhookId}") {
            jsonParams(request, ModifyWebhookRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(WebhookObject.serializer())
    }
    
    suspend fun modifyWebhookWithToken(
        webhookId: Snowflake,
        webhookToken: String,
        request: ModifyWebhookRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/webhooks/${webhookId}/${webhookToken}") {
            jsonParams(request, ModifyWebhookRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(WebhookObject.serializer())
    }
    
    suspend fun deleteWebhook(
        webhookId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/webhooks/${webhookId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    } 
    
    suspend fun deleteWebhookWithToken(
        webhookId: Snowflake,
        webhookToken: String,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/webhooks/${webhookId}/${webhookToken}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun executeWebhook(
        webhookId: Snowflake,
        webhookToken: String,
        request: ExecuteWebhookRequestParameters,
        wait: Boolean? = null,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageObject?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/webhooks/${webhookId}/${webhookToken}") {
            jsonParams(request, ExecuteWebhookRequestParameters.serializer())
            optionalQueryStringParam("wait", wait)
            optionalQueryStringParam("threadId", threadId)
        }
        val response = httpClient.submit(httpRequest)

        if (wait == null || !wait) {
            return response.withData { null }
        } else {
            return response.withData(MessageObject.serializer())
        }
    }
    
    // TODO: Figure out how these work
//    fun executeSlackCompatibleWebhook(
//        webhookId: Snowflake,
//        webhookToken: Snowflake,
//        wait: Boolean? = null,
//        threadId: Snowflake? = null
//    ): CwHttpResponse<Unit> {
//        TODO("To be implemented")
//    }
//    
//    fun executeGitHubCompatibleWebhook(
//        webhookId: Snowflake,
//        webhookToken: String,
//        wait: Boolean? = null,
//        threadId: Snowflake? = null
//    ): CwHttpResponse<Unit> {
//        TODO("To be implemented")
//    }
    
    suspend fun getWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/webhooks/${webhookId}/${webhookToken}/messages/${messageId}") {
            optionalQueryStringParam("thread_id", threadId)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun editWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        request: EditWebhookMessageRequestParameters,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/webhooks/${webhookId}/${webhookToken}/messages/${messageId}") {
            jsonParams(request, EditWebhookMessageRequestParameters.serializer())
            optionalQueryStringParam("thread_id", threadId)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(MessageObject.serializer())
    }
    
    suspend fun deleteWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        threadId: Snowflake? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/webhooks/${webhookId}/${webhookToken}/messages/${messageId}") {
            optionalQueryStringParam("thread_id", threadId)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}