package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.MessageEntity
import xyz.darkcomet.cogwheel.core.network.entities.WebhookEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.EditWebhookMessageRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ExecuteWebhookRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyWebhookRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class WebhookResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun createWebhook(channelId: Snowflake): CwHttpResponse<WebhookEntity> {
        TODO("To be implemented")
    }
    
    fun getChannelWebhooks(channelId: Snowflake): CwHttpResponse<List<WebhookEntity>> {
        TODO("To be implemented")
    }
    
    fun getGuildWebhooks(guildId: Snowflake): CwHttpResponse<List<WebhookEntity>> {
        TODO("To be implemented")
    }
    
    fun getWebhook(webhookId: Snowflake): CwHttpResponse<WebhookEntity> {
        TODO("To be implemented")
    }
    
    fun getWebhookWithToken(
        webhookId: Snowflake, 
        webhookToken: String
    ): CwHttpResponse<WebhookEntity> {
        TODO("To be implemented")
    }
    
    fun modifyWebhook(
        webhookId: Snowflake,
        request: ModifyWebhookRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookEntity> {
        TODO("To be implemented")
    }
    
    fun modifyWebhookWithToken(
        webhookId: Snowflake,
        webhookToken: String,
        request: ModifyWebhookRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<WebhookEntity> {
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
        request: ExecuteWebhookRequestEntity,
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
    ): CwHttpResponse<MessageEntity> {
        TODO("To be implemented")
    }
    
    fun editWebhookMessage(
        webhookId: Snowflake,
        webhookToken: String,
        messageId: Snowflake,
        request: EditWebhookMessageRequestEntity,
        threadId: Snowflake? = null
    ): CwHttpResponse<MessageEntity> {
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