package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.GuildAutoModerationRuleEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateGuildAutoModerationRuleRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyGuildAutoModerationRuleRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class AutoModerationResource internal constructor(httpClient: CwHttpClient) {

    fun listRules(guildId: Snowflake): CwHttpResponse<List<GuildAutoModerationRuleEntity>> {
        TODO("Not implemented yet")
    }

    fun getRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
        TODO("Not implemented yet")
    }

    fun createRule(
        guildId: Snowflake, 
        request: CreateGuildAutoModerationRuleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyRule(
        guildId: Snowflake,
        autoModerationRuleId: Snowflake,
        request: ModifyGuildAutoModerationRuleRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildAutoModerationRuleEntity> {
        TODO("Not implemented yet")
    }

    fun deleteRule(
        guildId: Snowflake, 
        autoModerationRuleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }
    
}