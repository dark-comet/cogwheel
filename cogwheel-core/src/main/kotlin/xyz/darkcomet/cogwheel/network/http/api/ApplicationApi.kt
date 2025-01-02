package xyz.darkcomet.cogwheel.network.http.api

import xyz.darkcomet.cogwheel.models.Snowflake
import xyz.darkcomet.cogwheel.network.entities.ApplicationEntity
import xyz.darkcomet.cogwheel.network.entities.ApplicationInstanceEntity
import xyz.darkcomet.cogwheel.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.network.entities.request.ModifyCurrentApplicationRequestEntity

class ApplicationApi
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getCurrent(): CwHttpResponse<ApplicationEntity?> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        return httpClient.submit(request).toEntity(ApplicationEntity.serializer())
    }
    
    suspend fun editCurrent(request: ModifyCurrentApplicationRequestEntity): CwHttpResponse<ApplicationEntity?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/applications/@me") {
            jsonParams(request, ModifyCurrentApplicationRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(ApplicationEntity.serializer())
    }
    
    // TODO: Make this testable in integration tests
    suspend fun getActivityInstance(applicationId: Snowflake, instanceId: String): CwHttpResponse<ApplicationInstanceEntity?> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        return httpClient.submit(request).toEntity(ApplicationInstanceEntity.serializer())
    }
    
//
//    fun getRoleConnectionMetadataRecords(applicationId: Snowflake): CwHttpResponse {
//        TODO("Not implemented yet")
//    }
//
//    fun updateRoleConnectionMetadataRecords(applicationId: Snowflake, request: UpdateApplicationRoleConnectionRecordsRequest): CwHttpResponse {
//        TODO("Not implemented yet")
//    }
    
}