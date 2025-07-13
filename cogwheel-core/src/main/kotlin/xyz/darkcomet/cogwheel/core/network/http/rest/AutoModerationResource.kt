package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationResource 
internal constructor(private val httpClient: CwHttpClient) {

    suspend fun listAutoModerationRules(guildId: Snowflake): CwHttpResponse<List<GuildAutoModerationRuleObject>> {
        val httpRequest = CwHttpRequest.create(GET, "/guilds/${guildId}/auto-moderation/rules")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildAutoModerationRuleObject.serializer()))
    }

    suspend fun getAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        
        val httpRequest = CwHttpRequest.create(
            GET, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/auto-moderation/rules/*"
        )
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildAutoModerationRuleObject.serializer())
    }

    suspend fun createAutoModerationRule(
        guildId: Snowflake,
        request: CreateGuildAutoModerationRuleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/guilds/${guildId}/auto-moderation/rules/") {
            jsonParams(request, CreateGuildAutoModerationRuleRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildAutoModerationRuleObject.serializer())
    }

    suspend fun modifyAutoModerationRule(
        guildId: Snowflake,
        autoModerationRuleId: Snowflake,
        request: ModifyGuildAutoModerationRuleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        
        val httpRequest = CwHttpRequest.create(
            PATCH, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/auto-moderation/rules/*"
        ) {
            jsonParams(request, ModifyGuildAutoModerationRuleRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildAutoModerationRuleObject.serializer())
    }

    suspend fun deleteAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(
            DELETE, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}",
            rateLimitRouteIdentifier = "/guilds/${guildId}/auto-moderation/rules/*"
        ) {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}