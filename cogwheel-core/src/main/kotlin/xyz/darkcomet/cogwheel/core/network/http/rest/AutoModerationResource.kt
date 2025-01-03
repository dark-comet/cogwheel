package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildAutoModerationRuleRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationResource internal constructor(httpClient: CwHttpClient) {

    fun listAutoModerationRulesForGuild(guildId: Snowflake): CwHttpResponse<List<GuildAutoModerationRuleObject>> {
        TODO("Not implemented yet")
    }

    fun getAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        TODO("Not implemented yet")
    }

    fun createAutoModerationRule(
        guildId: Snowflake,
        request: CreateGuildAutoModerationRuleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        TODO("Not implemented yet")
    }

    fun modifyAutoModerationRule(
        guildId: Snowflake,
        autoModerationRuleId: Snowflake,
        request: ModifyGuildAutoModerationRuleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleObject> {
        TODO("Not implemented yet")
    }

    fun deleteAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
}