@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.http.rest.AuditLogResource
import xyz.darkcomet.cogwheel.core.network.objects.GuildAuditLogObject
import xyz.darkcomet.cogwheel.framework.models.entitles.auditlog.GuildAuditLog
import xyz.darkcomet.cogwheel.framework.models.entitles.auditlog.toModel
import xyz.darkcomet.cogwheel.framework.models.request.GetGuildAuditLogRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.RequestInvocation1S
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future
import java.util.function.Consumer

class AuditLogApi
internal constructor(resource: AuditLogResource) {
    
    @JvmField
    val get = GetGuildAuditLogEndpoint(resource)
    
}

class GetGuildAuditLogEndpoint 
internal constructor(private val resource: AuditLogResource)
    : RequestInvocation1S<GuildId, GetGuildAuditLogRequestSpec, GuildAuditLog>() {
        
    override fun createRequest(guildId: GuildId): GetGuildAuditLogRequestSpec {
        return GetGuildAuditLogRequestSpec(guildId)
    }

    override suspend fun invoke(request: GetGuildAuditLogRequestSpec): Response<GuildAuditLog> {
        val response: CwHttpResponse<GuildAuditLogObject> = resource.getGuildAuditLog(
            guildId = request.guildId.snowflake,
            userId = request.userId,
            actionType = request.actionType,
            before = request.before,
            after = request.after,
        )
        
        val result = response.data?.toModel()
        
        return Response(result, response)
    }

    override suspend fun invoke(
        guildId: GuildId,
        request: GetGuildAuditLogRequestSpec?,
        config: (GetGuildAuditLogRequestSpec.() -> Unit)?
    ): Response<GuildAuditLog> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<GetGuildAuditLogRequestSpec>?
    ): Future<Response<GuildAuditLog>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<GetGuildAuditLogRequestSpec>?
    ): Response<GuildAuditLog> {
        return super.block(guildId, config)
    }
}