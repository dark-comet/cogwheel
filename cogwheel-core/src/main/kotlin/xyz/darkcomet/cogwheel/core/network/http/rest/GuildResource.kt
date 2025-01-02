package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.serializer
import xyz.darkcomet.cogwheel.core.network.entities.*
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.core.network.entities.request.*
import xyz.darkcomet.cogwheel.core.network.entities.response.GuildBeginPruneResponseEntity
import xyz.darkcomet.cogwheel.core.network.entities.response.GuildBulkBanResponseEntity
import xyz.darkcomet.cogwheel.core.network.entities.response.GuildGetPruneCountResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.entities.response.ListGuildActiveThreadsResponseEntity

class GuildResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun create(request: CreateGuildRequestEntity): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds") {
            jsonParams(request, CreateGuildRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }
    
    suspend fun get(guildId: Snowflake, withCounts: Boolean = false): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}") {
            queryStringParam("with_counts", withCounts.toString())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }

    suspend fun getPreview(guildId: Snowflake): CwHttpResponse<GuildPreviewEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/preview")
        return httpClient.submit(httpRequest).toEntity(GuildPreviewEntity.serializer())
    }

    suspend fun modify(guildId: Snowflake, request: ModifyGuildRequestEntity): CwHttpResponse<GuildEntity> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}") {
            jsonParams(request, ModifyGuildRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(GuildEntity.serializer())
    }

    suspend fun delete(guildId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}")
        return httpClient.submit(httpRequest).toEntity(Unit.serializer())
    }

    fun getChannels(guildId: Snowflake): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun createChannel(
        guildId: Snowflake, 
        request: CreateGuildChannelRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<ChannelEntity> {
        TODO("Not implemented yet")
    }

    fun modifyChannelPositions(
        guildId: Snowflake, 
        request: ModifyGuildChannelPositionsRequestEntity
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun listActiveThreads(guildId: Snowflake): CwHttpResponse<ListGuildActiveThreadsResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getMember(guildId: Snowflake, userId: Snowflake): CwHttpResponse<GuildMemberEntity> {
        TODO("Not implemented yet")
    }

    fun listMembers(
        guildId: Snowflake, 
        limit: Int? = null, 
        after: Snowflake? = null
    ): CwHttpResponse<List<GuildMemberEntity>> {
        TODO("Not implemented yet")
    }

    fun searchMembers(
        guildId: Snowflake, 
        query: String,
        limit: Int = 1
    ): CwHttpResponse<List<GuildMemberEntity>> {
        TODO("Not implemented yet")
    }

    fun addMember(
        guildId: Snowflake, 
        userId: Snowflake,
        request: AddGuildMemberRequestEntity
    ): CwHttpResponse<GuildMemberEntity> {
        TODO("Not implemented yet")
    }

    fun modifyMember(
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

    fun addMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeMemberRole(
        guildId: Snowflake, 
        userId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeMember(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun getBans(
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

    fun createBan(
        guildId: Snowflake, 
        userId: Snowflake, 
        request: CreateGuildBanRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun removeBan(
        guildId: Snowflake, 
        userId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun bulkBan(
        guildId: Snowflake, 
        request: BulkGuildBanRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildBulkBanResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getRoles(guildId: Snowflake): CwHttpResponse<List<GuildRoleEntity>> {
        TODO("Not implemented yet")
    }

    fun getRole(guildId: Snowflake, roleId: Snowflake): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun createRole(
        guildId: Snowflake, 
        request: CreateGuildRoleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyRolePositions(
        guildId: Snowflake, 
        request: List<ModifyGuildRolePositionRequestEntity>, 
        auditLogReason: String? = null
    ): CwHttpResponse<List<GuildRoleEntity>> {
        TODO("Not implemented yet")
    }

    fun modifyRole(
        guildId: Snowflake, 
        roleId: Snowflake, 
        request: ModifyGuildRoleRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildRoleEntity> {
        TODO("Not implemented yet")
    }

    fun modifyMfaLevel(
        guildId: Snowflake, 
        request: ModifyGuildMfaLevelRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<Int> {
        TODO("Not implemented yet")
    }

    fun deleteRole(
        guildId: Snowflake, 
        roleId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        TODO("Not implemented yet")
    }

    fun getPruneCount(
        guildId: Snowflake, 
        days: Int? = null, 
        includeRoleIds: List<Snowflake>? = null
    ): CwHttpResponse<GuildGetPruneCountResponseEntity> {
        TODO("Not implemented yet")
    }

    fun beginPrune(
        guildId: Snowflake, 
        request: BeginGuildPruneRequestEntity, 
        auditLogReason: String?
    ): CwHttpResponse<GuildBeginPruneResponseEntity> {
        TODO("Not implemented yet")
    }

    fun getVoiceRegions(guildId: Snowflake): CwHttpResponse<List<VoiceRegionEntity>> {
        TODO("Not implemented yet")
    }

    fun getInvites(guildId: Snowflake): CwHttpResponse<List<InviteEntity>> {
        TODO("Not implemented yet")
    }

    fun getIntegrations(guildId: Snowflake): CwHttpResponse<List<GuildIntegrationEntity>> {
        TODO("Not implemented yet")
    }

    fun deleteIntegration(
        guildId: Snowflake, 
        integrationId: Snowflake, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildIntegrationEntity> {
        TODO("Not implemented yet")
    }

    fun getWidgetSettings(guildId: Snowflake): CwHttpResponse<GuildWidgetSettingsEntity> {
        TODO("Not implemented yet")
    }

    fun modifyWidgetSettings(
        guildId: Snowflake, 
        request: ModifyGuildWidgetRequestEntity, 
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWidgetSettingsEntity> {
        TODO("Not implemented yet")
    }

    fun getWidget(guildId: Snowflake): CwHttpResponse<GuildWidgetEntity> {
        TODO("Not implemented yet")
    }

    fun getVanityUrl(guildId: Snowflake): CwHttpResponse<InviteEntity> {
        TODO("Not implemented yet")
    }
    
    fun getWidgetImage(guildId: Snowflake, style: String? = null): CwHttpResponse<String> {
        TODO("Not implemented yet")
    }

    fun getWelcomeScreen(guildId: Snowflake): CwHttpResponse<GuildWelcomeScreenEntity> {
        TODO("Not implemented yet")
    }

    fun modifyWelcomeScreen(
        guildId: Snowflake, 
        request: ModifyGuildWelcomeScreenRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildWelcomeScreenEntity> {
        TODO("Not implemented yet")
    }

    fun getOnboarding(guildId: Snowflake): CwHttpResponse<GuildOnboardingEntity> {
        TODO("Not implemented yet")
    }

    fun modifyOnboarding(
        guildId: Snowflake, 
        request: ModifyGuildOnboardingRequestEntity, 
        auditLogReason: String?
    ): CwHttpResponse<GuildOnboardingEntity> {
        TODO("Not implemented yet")
    }
    
}