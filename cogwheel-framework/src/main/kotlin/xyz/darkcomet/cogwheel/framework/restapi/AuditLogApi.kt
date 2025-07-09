@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.http.rest.AuditLogResource
import xyz.darkcomet.cogwheel.core.network.objects.GuildAuditLogObject
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildAuditLog
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.specs.auditlog.GetGuildAuditLogRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.Response

class AuditLogApi
internal constructor(private val resource: AuditLogResource) {
    
    @JvmField
    val getForGuild = GetGuildAuditLogEndpoint(resource)
    
}

class GetGuildAuditLogEndpoint 
internal constructor(private val resource: AuditLogResource)
    : RequestEndpoint1S<GuildId, GetGuildAuditLogRequestSpec, GuildAuditLog>() {
        
    override fun createRequest(guildId: GuildId): GetGuildAuditLogRequestSpec {
        return GetGuildAuditLogRequestSpec(guildId)
    }

    override suspend fun invoke(guildId: GuildId, request: GetGuildAuditLogRequestSpec): Response<GuildAuditLog> {
        val response: CwHttpResponse<GuildAuditLogObject> = resource.getGuildAuditLog(
            guildId = guildId.snowflake,
            userId = request.userId,
            actionType = request.actionType,
            before = request.before,
            after = request.after,
        )
        
        val result = response.data?.toModel()
        
        return Response(result, response)
    }


}