package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationResource 
internal constructor(private val httpClient: CwHttpClient) {

    suspend fun listAutoModerationRulesForGuild(guildId: Snowflake): CwHttpResponse<List<GuildAutoModerationRuleObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/auto-moderation/rules")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildAutoModerationRuleObject.serializer()))
    }

    suspend fun getAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildAutoModerationRuleObject.serializer())
    }

    suspend fun createAutoModerationRule(
        guildId: Snowflake,
        request: CreateGuildAutoModerationRuleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/auto-moderation/rules/") {
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
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}") {
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
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/auto-moderation/rules/${autoModerationRuleId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}