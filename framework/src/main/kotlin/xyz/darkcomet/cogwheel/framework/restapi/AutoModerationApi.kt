@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.AutoModerationResource
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.GuildAutoModerationRule
import xyz.darkcomet.cogwheel.framework.models.entitles.automod.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.DeleteGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildAutoModerationRuleRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*
import java.util.concurrent.Future
import java.util.function.Consumer

class AutoModerationApi
internal constructor(resource: AutoModerationResource) {
    
    @JvmField
    val list = ListGuildAutoModerationRulesEndpoint(resource)
    
    @JvmField
    val get = GetGuildAutoModerationRuleEndpoint(resource)
    
    @JvmField
    val create = CreateGuildAutoModerationRuleEndpoint(resource)
    
    @JvmField
    val modify = ModifyGuildAutoModerationRuleEndpoint(resource)
    
    @JvmField
    val delete = DeleteGuildAutoModerationRuleEndpoint(resource)
    
}

class ListGuildAutoModerationRulesEndpoint
internal constructor(private val resource: AutoModerationResource)
    : Invocation1<GuildId, List<GuildAutoModerationRule>>() {
        
    override suspend fun invoke(guildId: GuildId): Response<List<GuildAutoModerationRule>> {
        val response = resource.listAutoModerationRules(guildId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

}

class GetGuildAutoModerationRuleEndpoint
internal constructor(private val resource: AutoModerationResource)
    : Invocation2<GuildId, AutoModerationRuleId, GuildAutoModerationRule>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        ruleId: AutoModerationRuleId
    ): Response<GuildAutoModerationRule> {
        val response = resource.getAutoModerationRule(guildId.snowflake, ruleId.snowflake)
        val result = response.data?.toModel()
        
        return Response(result, response)
    }
    
}

class CreateGuildAutoModerationRuleEndpoint
internal constructor(private val resource: AutoModerationResource)
    : RequestInvocation1S<GuildId, CreateGuildAutoModerationRuleRequestSpec, GuildAutoModerationRule>() {
        
    override fun createRequest(guildId: GuildId): CreateGuildAutoModerationRuleRequestSpec {
        return CreateGuildAutoModerationRuleRequestSpec(guildId)
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

    override suspend fun invoke(
        guildId: GuildId,
        request: CreateGuildAutoModerationRuleRequestSpec?,
        config: (CreateGuildAutoModerationRuleRequestSpec.() -> Unit)?
    ): Response<GuildAutoModerationRule> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<CreateGuildAutoModerationRuleRequestSpec>?
    ): Future<Response<GuildAutoModerationRule>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<CreateGuildAutoModerationRuleRequestSpec>?
    ): Response<GuildAutoModerationRule> {
        return super.block(guildId, config)
    }
}

class ModifyGuildAutoModerationRuleEndpoint
internal constructor(private val resource: AutoModerationResource)
    : RequestInvocation2S<GuildId, AutoModerationRuleId, ModifyGuildAutoModerationRuleRequestSpec, GuildAutoModerationRule>() {
        
    override fun createRequest(
        guildId: GuildId,
        ruleId: AutoModerationRuleId
    ): ModifyGuildAutoModerationRuleRequestSpec {
        return ModifyGuildAutoModerationRuleRequestSpec(guildId, ruleId)
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

    override fun async(
        guildId: GuildId,
        ruleId: AutoModerationRuleId,
        config: Consumer<ModifyGuildAutoModerationRuleRequestSpec>?
    ): Future<Response<GuildAutoModerationRule>> {
        return super.async(guildId, ruleId, config)
    }

    override fun block(
        guildId: GuildId,
        ruleId: AutoModerationRuleId,
        config: Consumer<ModifyGuildAutoModerationRuleRequestSpec>?
    ): Response<GuildAutoModerationRule> {
        return super.block(guildId, ruleId, config)
    }
}

class DeleteGuildAutoModerationRuleEndpoint
internal constructor(private val resource: AutoModerationResource)
    : RequestInvocation2S<GuildId, AutoModerationRuleId, DeleteGuildAutoModerationRuleRequestSpec, Unit>() {
        
    override fun createRequest(
        guildId: GuildId,
        ruleId: AutoModerationRuleId
    ): DeleteGuildAutoModerationRuleRequestSpec {
        return DeleteGuildAutoModerationRuleRequestSpec(guildId, ruleId)
    }
    
    override suspend fun invoke(request: DeleteGuildAutoModerationRuleRequestSpec): Response<Unit> {
        val response = resource.deleteAutoModerationRule(
            request.guildId.snowflake,
            request.ruleId.snowflake,
            request.auditLogReason
        )
        
        return Response(Unit, response)
    }

    override fun async(
        guildId: GuildId,
        ruleId: AutoModerationRuleId,
        config: Consumer<DeleteGuildAutoModerationRuleRequestSpec>?
    ): Future<Response<Unit>> {
        return super.async(guildId, ruleId, config)
    }

    override fun block(
        guildId: GuildId,
        ruleId: AutoModerationRuleId,
        config: Consumer<DeleteGuildAutoModerationRuleRequestSpec>?
    ): Response<Unit> {
        return super.block(guildId, ruleId, config)
    }
}