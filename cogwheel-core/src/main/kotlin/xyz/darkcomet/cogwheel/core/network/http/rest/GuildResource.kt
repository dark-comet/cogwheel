package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.serializer
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.core.network.objects.request.*
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildBeginPruneResponseEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildBulkBanResponseEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.GuildGetPruneCountResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.response.ListGuildActiveThreadsResponseEntity

class GuildResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun createGuild(request: CreateGuildRequestEntity): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds") {
            jsonParams(request, CreateGuildRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }
    
    suspend fun getGuild(guildId: Snowflake, withCounts: Boolean = false): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}") {
            queryStringParam("with_counts", withCounts.toString())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }

    suspend fun getGuildPreview(guildId: Snowflake): CwHttpResponse<GuildPreviewEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/preview")
        return httpClient.submit(httpRequest).toEntity(GuildPreviewEntity.serializer())
    }

    suspend fun modifyGuild(guildId: Snowflake, request: ModifyGuildRequestEntity): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}") {
            jsonParams(request, ModifyGuildRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }

    suspend fun deleteGuild(guildId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}")
        return httpClient.submit(httpRequest).toEntity(Unit.serializer())
    }

    fun getGuildChannels(guildId: Snowflake): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun createGuildChannel(
        guildId: Snowflake, 
        request: CreateGuildChannelRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildChannelPositions(
        guildId: Snowflake, 
        request: ModifyGuildChannelPositionsRequestEntity
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun listActiveGuildThreads(guildId: Snowflake): CwHttpResponse<ListGuildActiveThreadsResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildMember(guildId: Snowflake, userId: Snowflake): CwHttpResponse<GuildMemberEntity> {
        TODO("Not implemented yet")
    }

    fun listGuildMembers(
        guildId: Snowflake, 
        limit: Int? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildMemberEntity>> {
        TODO("Not implemented yet")
    }

    fun searchGuildMembers(
        guildId: Snowflake, 
        query: String,
        limit: Int = 1
    ): CwHttpResponse<List<GuildMemberEntity>> {
        TODO("Not implemented yet")
    }

    fun addGuildMember(
        guildId: Snowflake, 
        userId: Snowflake,
        request: AddGuildMemberRequestEntity
    ): CwHttpResponse<GuildMemberEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildMember(
        guildId: Snowflake, 
        userId: Snowflake, 
        request: ModifyGuildMemberRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberEntity> {
        TODO("Not implemented yet")
    }

    fun modifyCurrentMember(
        guildId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildMemberEntity> {
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
    ): CwHttpResponse<List<GuildBanEntity>> {
        TODO("Not implemented yet")
    }

    fun getBan(guildId: Snowflake, userId: Snowflake): CwHttpResponse<GuildBanEntity> {
        TODO("Not implemented yet")
    }

    fun createGuildBan(
        guildId: Snowflake, 
        userId: Snowflake, 
        request: CreateGuildBanRequestEntity, 
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
        request: BulkGuildBanRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildBulkBanResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildRoles(guildId: Snowflake): CwHttpResponse<List<GuildRoleEntity>> {
        TODO("Not implemented yet")
    }

    fun getGuildRole(guildId: Snowflake, roleId: Snowflake): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun createGuildRole(
        guildId: Snowflake, 
        request: CreateGuildRoleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildRolePositions(
        guildId: Snowflake, 
        request: List<ModifyGuildRolePositionRequestEntity>, 
        auditLogReason: String? = null
    ): CwHttpResponse<List<GuildRoleEntity>> {
        TODO("Not implemented yet")
    }

    fun modifyGuildRole(
        guildId: Snowflake, 
        roleId: Snowflake, 
        request: ModifyGuildRoleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildMfaLevel(
        guildId: Snowflake, 
        request: ModifyGuildMfaLevelRequestEntity, 
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
    ): CwHttpResponse<GuildGetPruneCountResponseEntity> {
        TODO("Not implemented yet")
    }

    fun beginGuildPrune(
        guildId: Snowflake, 
        request: BeginGuildPruneRequestEntity, 
        auditLogReason: String?
    ): CwHttpResponse<GuildBeginPruneResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildVoiceRegions(guildId: Snowflake): CwHttpResponse<List<VoiceRegionEntity>> {
        TODO("Not implemented yet")
    }

    fun getGuildInvites(guildId: Snowflake): CwHttpResponse<List<InviteEntity>> {
        TODO("Not implemented yet")
    }

    fun getGuildIntegrations(guildId: Snowflake): CwHttpResponse<List<GuildIntegrationEntity>> {
        TODO("Not implemented yet")
    }

    fun deleteGuildIntegration(
        guildId: Snowflake, 
        integrationId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildIntegrationEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildWidgetSettings(guildId: Snowflake): CwHttpResponse<GuildWidgetSettingsEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildWidgetSettings(
        guildId: Snowflake, 
        request: ModifyGuildWidgetRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWidgetSettingsEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildWidget(guildId: Snowflake): CwHttpResponse<GuildWidgetEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildVanityUrl(guildId: Snowflake): CwHttpResponse<InviteEntity> {
        TODO("Not implemented yet")
    }
    
    fun getGuildWidgetImage(guildId: Snowflake, style: String? = null): CwHttpResponse<String> {
        TODO("Not implemented yet")
    }

    fun getGuildWelcomeScreen(guildId: Snowflake): CwHttpResponse<GuildWelcomeScreenEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildWelcomeScreen(
        guildId: Snowflake, 
        request: ModifyGuildWelcomeScreenRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWelcomeScreenEntity> {
        TODO("Not implemented yet")
    }

    fun getGuildOnboarding(guildId: Snowflake): CwHttpResponse<GuildOnboardingEntity> {
        TODO("Not implemented yet")
    }

    fun modifyGuildOnboarding(
        guildId: Snowflake, 
        request: ModifyGuildOnboardingRequestEntity, 
        auditLogReason: String?
    ): CwHttpResponse<GuildOnboardingEntity> {
        TODO("Not implemented yet")
    }
    
}