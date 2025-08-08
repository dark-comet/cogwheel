@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.AutoModerationResource
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.GuildAutoModerationRule
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class AutoModerationApi
internal constructor(resource: AutoModerationResource) {
    
    @JvmField
    val list = object : Invocation1<GuildId, List<GuildAutoModerationRule>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<GuildAutoModerationRule>> {
            val response = resource.listAutoModerationRules(p1.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }
        
    }
    
    @JvmField
    val get = object : Invocation2<GuildId, AutoModerationRuleId, GuildAutoModerationRule>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: AutoModerationRuleId
        ): Response<GuildAutoModerationRule> {
            val response = resource.getAutoModerationRule(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()

            return Response(result, response)
        }
        
    }
    
    @JvmField
    val create = object : RequestInvocation1S<GuildId, CreateGuildAutoModerationRuleRequestSpec, GuildAutoModerationRule>() {
        
        override fun createRequest(p1: GuildId): CreateGuildAutoModerationRuleRequestSpec {
            return CreateGuildAutoModerationRuleRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateGuildAutoModerationRuleRequestSpec): Response<GuildAutoModerationRule> {
            val response = resource.createAutoModerationRule(
                request.guildId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }
        
    }
    
    @JvmField
    val modify = object : RequestInvocation2S<GuildId, AutoModerationRuleId, ModifyGuildAutoModerationRuleRequestSpec, GuildAutoModerationRule>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: AutoModerationRuleId
        ): ModifyGuildAutoModerationRuleRequestSpec {
            
            return ModifyGuildAutoModerationRuleRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyGuildAutoModerationRuleRequestSpec): Response<GuildAutoModerationRule> {
            val response = resource.modifyAutoModerationRule(
                request.guildId.snowflake,
                request.ruleId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val delete = object : RequestInvocation2S<GuildId, AutoModerationRuleId, DeleteGuildAutoModerationRuleRequestSpec, Boolean>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: AutoModerationRuleId
        ): DeleteGuildAutoModerationRuleRequestSpec {
            
            return DeleteGuildAutoModerationRuleRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: DeleteGuildAutoModerationRuleRequestSpec): Response<Boolean> {
            val response = resource.deleteAutoModerationRule(
                request.guildId.snowflake,
                request.ruleId.snowflake,
                request.auditLogReason
            )
            val result = response.raw.success

            return Response(result, response)
        }

    }
    
}