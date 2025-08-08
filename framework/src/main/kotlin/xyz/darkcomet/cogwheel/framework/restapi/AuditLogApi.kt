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
    val get = object : RequestInvocation1S<GuildId, GetGuildAuditLogRequestSpec, GuildAuditLog>() {
        
        override fun createRequest(p1: GuildId): GetGuildAuditLogRequestSpec {
            return GetGuildAuditLogRequestSpec(p1)
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
        
    }
    
}