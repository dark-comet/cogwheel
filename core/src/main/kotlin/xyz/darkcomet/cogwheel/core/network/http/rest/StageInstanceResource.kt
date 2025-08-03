package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.CreateStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.StageInstanceObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StageInstanceResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun createStageInstance(
        request: CreateStageInstanceRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StageInstanceObject> {
        
        val httpRequest = CwHttpRequest.create(POST, "/stage-instances") {
            jsonParams(request, CreateStageInstanceRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(StageInstanceObject.serializer())
    }
    
    suspend fun getStageInstance(channelId: Snowflake): CwHttpResponse<StageInstanceObject> {
        val httpRequest = CwHttpRequest.create(GET, "/stage-instances/${channelId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(StageInstanceObject.serializer())
    }
    
    suspend fun modifyStageInstance(
        channelId: Snowflake,
        request: ModifyStageInstanceRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<StageInstanceObject> {
        
        val httpRequest = CwHttpRequest.create(PATCH, "/stage-instances/${channelId}") {
            jsonParams(request, ModifyStageInstanceRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(StageInstanceObject.serializer())
    }
    
    suspend fun deleteStageInstance(
        channelId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        
        val httpRequest = CwHttpRequest.create(DELETE, "/stage-instances/${channelId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}