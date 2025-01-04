package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.serializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.objects.request.*
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildBeginPruneResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildBulkBanResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildGetPruneCountResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.response.ListGuildActiveThreadsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun createGuild(request: CreateGuildRequestParameters): CwHttpResponse<GuildObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds") {
            jsonParams(request, CreateGuildRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GuildObject.serializer())
    }
    
    suspend fun getGuild(guildId: Snowflake, withCounts: Boolean = false): CwHttpResponse<GuildObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}") {
            queryStringParam("with_counts", withCounts.toString())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GuildObject.serializer())
    }

    suspend fun getGuildPreview(guildId: Snowflake): CwHttpResponse<GuildPreviewObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/preview")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GuildPreviewObject.serializer())
    }

    suspend fun modifyGuild(guildId: Snowflake, request: ModifyGuildRequestParameters): CwHttpResponse<GuildObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}") {
            jsonParams(request, ModifyGuildRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GuildObject.serializer())
    }

    suspend fun deleteGuild(guildId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(Unit.serializer())
    }

    fun getGuildChannels(guildId: Snowflake): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }

    fun createGuildChannel(
        guildId: Snowflake,
        request: CreateGuildChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildChannelPositions(
        guildId: Snowflake, 
        request: ModifyGuildChannelPositionsRequestParameters
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun listActiveGuildThreads(guildId: Snowflake): CwHttpResponse<ListGuildActiveThreadsResponseObject> {
        TODO("Not implemented yet")
    }

    fun getGuildMember(guildId: Snowflake, userId: Snowflake): CwHttpResponse<GuildMemberObject> {
        TODO("Not implemented yet")
    }

    fun listGuildMembers(
        guildId: Snowflake, 
        limit: Int? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildMemberObject>> {
        TODO("Not implemented yet")
    }

    fun searchGuildMembers(
        guildId: Snowflake, 
        query: String,
        limit: Int = 1
    ): CwHttpResponse<List<GuildMemberObject>> {
        TODO("Not implemented yet")
    }

    fun addGuildMember(
        guildId: Snowflake, 
        userId: Snowflake,
        request: AddGuildMemberRequestParameters
    ): CwHttpResponse<GuildMemberObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildMember(
        guildId: Snowflake,
        userId: Snowflake,
        request: ModifyGuildMemberRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberObject> {
        TODO("Not implemented yet")
    }

    fun modifyCurrentMember(
        guildId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberObject> {
        TODO("Not implemented yet")
    }

    fun addGuildMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeGuildMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeGuildMember(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun getGuildBans(
        guildId: Snowflake, 
        limit: Int? = null, 
        before: Snowflake? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildBanObject>> {
        TODO("Not implemented yet")
    }

    fun getBan(guildId: Snowflake, userId: Snowflake): CwHttpResponse<GuildBanObject> {
        TODO("Not implemented yet")
    }

    fun createGuildBan(
        guildId: Snowflake,
        userId: Snowflake,
        request: CreateGuildBanRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeGuildBan(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun bulkGuildBan(
        guildId: Snowflake,
        request: BulkGuildBanRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildBulkBanResponseObject> {
        TODO("Not implemented yet")
    }

    fun getGuildRoles(guildId: Snowflake): CwHttpResponse<List<GuildRoleObject>> {
        TODO("Not implemented yet")
    }

    fun getGuildRole(guildId: Snowflake, roleId: Snowflake): CwHttpResponse<GuildRoleObject> {
        TODO("Not implemented yet")
    }

    fun createGuildRole(
        guildId: Snowflake,
        request: CreateGuildRoleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildRolePositions(
        guildId: Snowflake,
        request: List<ModifyGuildRolePositionRequestParameters>,
        auditLogReason: String? = null
    ): CwHttpResponse<List<GuildRoleObject>> {
        TODO("Not implemented yet")
    }

    fun modifyGuildRole(
        guildId: Snowflake,
        roleId: Snowflake,
        request: ModifyGuildRoleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildMfaLevel(
        guildId: Snowflake,
        request: ModifyGuildMfaLevelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Int> {
        TODO("Not implemented yet")
    }

    fun deleteGuildRole(
        guildId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun getGuildPruneCount(
        guildId: Snowflake, 
        days: Int? = null, 
        includeRoleIds: List<Snowflake>? = null
    ): CwHttpResponse<GuildGetPruneCountResponseObject> {
        TODO("Not implemented yet")
    }

    fun beginGuildPrune(
        guildId: Snowflake,
        request: BeginGuildPruneRequestParameters,
        auditLogReason: String?
    ): CwHttpResponse<GuildBeginPruneResponseObject> {
        TODO("Not implemented yet")
    }

    fun getGuildVoiceRegions(guildId: Snowflake): CwHttpResponse<List<VoiceRegionObject>> {
        TODO("Not implemented yet")
    }

    fun getGuildInvites(guildId: Snowflake): CwHttpResponse<List<InviteObject>> {
        TODO("Not implemented yet")
    }

    fun getGuildIntegrations(guildId: Snowflake): CwHttpResponse<List<GuildIntegrationObject>> {
        TODO("Not implemented yet")
    }

    fun deleteGuildIntegration(
        guildId: Snowflake, 
        integrationId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildIntegrationObject> {
        TODO("Not implemented yet")
    }

    fun getGuildWidgetSettings(guildId: Snowflake): CwHttpResponse<GuildWidgetSettingsObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildWidgetSettings(
        guildId: Snowflake,
        request: ModifyGuildWidgetRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWidgetSettingsObject> {
        TODO("Not implemented yet")
    }

    fun getGuildWidget(guildId: Snowflake): CwHttpResponse<GuildWidgetObject> {
        TODO("Not implemented yet")
    }

    fun getGuildVanityUrl(guildId: Snowflake): CwHttpResponse<InviteObject> {
        TODO("Not implemented yet")
    }
    
    fun getGuildWidgetImage(
        guildId: Snowflake, 
        style: String? = null
    ): CwHttpResponse<String> {
        TODO("Not implemented yet")
    }

    fun getGuildWelcomeScreen(guildId: Snowflake): CwHttpResponse<GuildWelcomeScreenObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildWelcomeScreen(
        guildId: Snowflake,
        request: ModifyGuildWelcomeScreenRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWelcomeScreenObject> {
        TODO("Not implemented yet")
    }

    fun getGuildOnboarding(guildId: Snowflake): CwHttpResponse<GuildOnboardingObject> {
        TODO("Not implemented yet")
    }

    fun modifyGuildOnboarding(
        guildId: Snowflake,
        request: ModifyGuildOnboardingRequestParameters,
        auditLogReason: String?
    ): CwHttpResponse<GuildOnboardingObject> {
        TODO("Not implemented yet")
    }
    
}