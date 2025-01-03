package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.MessageObject
import xyz.darkcomet.cogwheel.core.network.objects.WebhookObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.EditWebhookMessageRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ExecuteWebhookRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyWebhookRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class WebhookResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun createWebhook(channelId: Snowflake): CwHttpResponse<WebhookObject> {
        TODO("To be implemented")
    }
    
    fun getChannelWebhooks(channelId: Snowflake): CwHttpResponse<List<WebhookObject>> {
        TODO("To be implemented")
    }
    
    fun getGuildWebhooks(guildId: Snowflake): CwHttpResponse<List<WebhookObject>> {
        TODO("To be implemented")
    }
    
    fun getWebhook(webhookId: Snowflake): CwHttpResponse<WebhookObject> {
        TODO("To be implemented")
    }
    
    fun getWebhookWithToken(
        webhookId: Snowflake, 
        webhookToken: String
    ): CwHttpResponse<WebhookObject> {
        TODO("To be implemented")
    }
    
    fun modifyWebhook(
        webhookId: Snowflake,
        request: ModifyWebhookRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookObject> {
        TODO("To be implemented")
    }
    
    fun modifyWebhookWithToken(
        webhookId: Snowflake,
        webhookToken: String,
        request: ModifyWebhookRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookObject> {
        TODO("To be implemented")
    }
    
    fun deleteWebhook(
        webhookId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    } 
    
    fun deleteWebhookWithToken(
        webhookId: Snowflake,
        token: String,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun executeWebhook(
        webhookId: Snowflake,
        webhookToken: String,
        request: ExecuteWebhookRequestParameters,
        wait: Boolean? = null,
        threadId: Snowflake? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun executeSlackCompatibleWebhook(
        webhookId: Snowflake,
        webhookToken: Snowflake,
        wait: Boolean? = null,
        threadId: Snowflake? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun executeGitHubCompatibleWebhook(
        webhookId: Snowflake,
        webhookToken: String,
        wait: Boolean? = null,
        threadId: Snowflake? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun getWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageObject> {
        TODO("To be implemented")
    }
    
    fun editWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        request: EditWebhookMessageRequestParameters,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageObject> {
        TODO("To be implemented")
    }
    
    fun deleteWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        threadId: Snowflake? = null
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}