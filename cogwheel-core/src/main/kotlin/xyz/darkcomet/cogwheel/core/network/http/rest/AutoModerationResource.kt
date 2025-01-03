package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildAutoModerationRuleRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildAutoModerationRuleRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationResource internal constructor(httpClient: CwHttpClient) {

    fun listAutoModerationRulesForGuild(guildId: Snowflake): CwHttpResponse<List<GuildAutoModerationRuleEntity>> {
        TODO("Not implemented yet")
    }

    fun getAutoModerationRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
        TODO("Not implemented yet")
    }

    fun createAutoModerationRule(
        guildId: Snowflake, 
        request: CreateGuildAutoModerationRuleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyAutoModerationRule(
        guildId: Snowflake,
        autoModerationRuleId: Snowflake,
        request: ModifyGuildAutoModerationRuleRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
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