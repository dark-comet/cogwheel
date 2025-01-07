package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.objects.GuildBeginPruneResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildBulkBanResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildGetPruneCountResponseObject
import xyz.darkcomet.cogwheel.core.network.objects.ListGuildActiveThreadsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import java.util.stream.Collectors

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
        
        return response.withNoData()
    }

    suspend fun getGuildChannels(guildId: Snowflake): CwHttpResponse<List<ChannelObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/channels")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(ChannelObject.serializer()))
    }

    suspend fun createGuildChannel(
        guildId: Snowflake,
        request: CreateGuildChannelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/channels") {
            jsonParams(request, CreateGuildChannelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }

    suspend fun modifyGuildChannelPositions(
        guildId: Snowflake, 
        request: List<ModifyGuildChannelPositionsRequestParameters>
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/channels") {
            jsonParams(request, ListSerializer(ModifyGuildChannelPositionsRequestParameters.serializer()))
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun listActiveGuildThreads(guildId: Snowflake): CwHttpResponse<ListGuildActiveThreadsResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/threads/active")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListGuildActiveThreadsResponseObject.serializer())
    }

    suspend fun getGuildMember(
        guildId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<GuildMemberObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/member/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildMemberObject.serializer())
    }

    suspend fun listGuildMembers(
        guildId: Snowflake, 
        limit: Int? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildMemberObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/members") {
            optionalQueryStringParam("limit", limit)
            optionalQueryStringParam("after", after)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildMemberObject.serializer()))
    }

    suspend fun searchGuildMembers(
        guildId: Snowflake, 
        query: String,
        limit: Int = 1
    ): CwHttpResponse<List<GuildMemberObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/members/search") {
            queryStringParam("query", query)
            optionalQueryStringParam("limit", limit)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildMemberObject.serializer()))
    }

    suspend fun addGuildMember(
        guildId: Snowflake, 
        userId: Snowflake,
        request: AddGuildMemberRequestParameters
    ): CwHttpResponse<GuildMemberObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/members/${userId}") {
            jsonParams(request, AddGuildMemberRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildMemberObject.serializer())
    }

    suspend fun modifyGuildMember(
        guildId: Snowflake,
        userId: Snowflake,
        request: ModifyGuildMemberRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/members/${userId}") {
            jsonParams(request, ModifyGuildMemberRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildMemberObject.serializer())
    }

    suspend fun modifyCurrentMember(
        guildId: Snowflake,
        request: ModifyGuildCurrentMemberRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/members/@me") {
            jsonParams(request, ModifyGuildCurrentMemberRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildMemberObject.serializer())
    }

    suspend fun addGuildMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/members/${userId}/roles/${roleId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun removeGuildMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/members/${userId}/roles/${roleId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun removeGuildMember(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/members/${userId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun getGuildBans(
        guildId: Snowflake, 
        limit: Int? = null, 
        before: Snowflake? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildBanObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/bans") {
            optionalQueryStringParam("limit", limit)
            optionalQueryStringParam("before", before)
            optionalQueryStringParam("after", after)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildBanObject.serializer()))
    }

    suspend fun getGuildBan(
        guildId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<GuildBanObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/bans/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildBanObject.serializer())
    }

    suspend fun createGuildBan(
        guildId: Snowflake,
        userId: Snowflake,
        request: CreateGuildBanRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/bans/${userId}") {
            jsonParams(request, CreateGuildBanRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun removeGuildBan(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/bans/${userId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun bulkGuildBan(
        guildId: Snowflake,
        request: BulkGuildBanRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildBulkBanResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/bulk-ban") {
            jsonParams(request, BulkGuildBanRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildBulkBanResponseObject.serializer())
    }

    suspend fun getGuildRoles(guildId: Snowflake): CwHttpResponse<List<RoleObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/roles")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(RoleObject.serializer()))
    }

    suspend fun getGuildRole(guildId: Snowflake, roleId: Snowflake): CwHttpResponse<RoleObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/roles/${roleId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(RoleObject.serializer())
    }

    suspend fun createGuildRole(
        guildId: Snowflake,
        request: CreateGuildRoleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<RoleObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/roles") {
            jsonParams(request, CreateGuildRoleRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(RoleObject.serializer())
    }

    suspend fun modifyGuildRolePositions(
        guildId: Snowflake,
        request: List<ModifyGuildRolePositionRequestParameters>,
        auditLogReason: String? = null
    ): CwHttpResponse<List<RoleObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/roles") {
            jsonParams(request, ListSerializer(ModifyGuildRolePositionRequestParameters.serializer()))
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(RoleObject.serializer()))
    }

    suspend fun modifyGuildRole(
        guildId: Snowflake,
        roleId: Snowflake,
        request: ModifyGuildRoleRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<RoleObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/roles/${roleId}") {
            jsonParams(request, ModifyGuildRoleRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(RoleObject.serializer())
    }

    suspend fun modifyGuildMfaLevel(
        guildId: Snowflake,
        request: ModifyGuildMfaLevelRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<Int> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/mfa") {
            jsonParams(request, ModifyGuildMfaLevelRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(Int.serializer())
    }

    suspend fun deleteGuildRole(
        guildId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/roles/${roleId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }

    suspend fun getGuildPruneCount(
        guildId: Snowflake, 
        days: Int? = null, 
        includeRoleIds: List<Snowflake>? = null
    ): CwHttpResponse<GuildGetPruneCountResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/prune") {
            optionalQueryStringParam("days", days)
            
            if (includeRoleIds != null) {
                val includeRolesValue = includeRoleIds.stream()
                    .map { roleId -> roleId.toString() }
                    .collect(Collectors.joining(","))
                
                queryStringParam("include_roles", includeRolesValue)
            }
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildGetPruneCountResponseObject.serializer())
    }

    suspend fun beginGuildPrune(
        guildId: Snowflake,
        request: BeginGuildPruneRequestParameters,
        auditLogReason: String?
    ): CwHttpResponse<GuildBeginPruneResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/prune") {
            jsonParams(request, BeginGuildPruneRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildBeginPruneResponseObject.serializer())
    }

    suspend fun getGuildVoiceRegions(guildId: Snowflake): CwHttpResponse<List<VoiceRegionObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/regions")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(VoiceRegionObject.serializer()))
    }

    suspend fun getGuildInvites(guildId: Snowflake): CwHttpResponse<List<InviteObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/invites")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(InviteObject.serializer()))
    }

    suspend fun getGuildIntegrations(guildId: Snowflake): CwHttpResponse<List<GuildIntegrationObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/integrations")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildIntegrationObject.serializer()))
    }

    suspend fun deleteGuildIntegration(
        guildId: Snowflake, 
        integrationId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildIntegrationObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/integrations/${integrationId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildIntegrationObject.serializer())
    }

    suspend fun getGuildWidgetSettings(guildId: Snowflake): CwHttpResponse<GuildWidgetSettingsObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/widget")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildWidgetSettingsObject.serializer())
    }

    suspend fun modifyGuildWidgetSettings(
        guildId: Snowflake,
        request: ModifyGuildWidgetSettingsRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWidgetSettingsObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/widget") {
            jsonParams(request, ModifyGuildWidgetSettingsRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildWidgetSettingsObject.serializer())
    }

    suspend fun getGuildWidget(guildId: Snowflake): CwHttpResponse<GuildWidgetObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/widget.json")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildWidgetObject.serializer())
    }

    suspend fun getGuildVanityUrl(guildId: Snowflake): CwHttpResponse<InviteObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/vanity-url")
        val response = httpClient.submit(httpRequest)

        return response.withData(InviteObject.serializer())
    }
    
    suspend fun getGuildWidgetImage(
        guildId: Snowflake, 
        style: String? = null
    ): CwHttpResponse<ImageData> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/widget.png") {
            optionalQueryStringParam("style", style)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ImageData.serializer())
    }

    suspend fun getGuildWelcomeScreen(guildId: Snowflake): CwHttpResponse<GuildWelcomeScreenObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/welcome-screen")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildWelcomeScreenObject.serializer())
    }

    suspend fun modifyGuildWelcomeScreen(
        guildId: Snowflake,
        request: ModifyGuildWelcomeScreenRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWelcomeScreenObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/welcome-screen") {
            jsonParams(request, ModifyGuildWelcomeScreenRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildWelcomeScreenObject.serializer())
    }

    suspend fun getGuildOnboarding(guildId: Snowflake): CwHttpResponse<GuildOnboardingObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/onboarding")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildOnboardingObject.serializer())
    }

    suspend fun modifyGuildOnboarding(
        guildId: Snowflake,
        request: ModifyGuildOnboardingRequestParameters,
        auditLogReason: String?
    ): CwHttpResponse<GuildOnboardingObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/onboarding") {
            jsonParams(request, ModifyGuildOnboardingRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildOnboardingObject.serializer())
    }
    
}