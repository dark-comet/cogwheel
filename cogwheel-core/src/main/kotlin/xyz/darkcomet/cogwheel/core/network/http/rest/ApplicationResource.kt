package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationEntity
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationActivityInstanceEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentApplicationRequestEntity

class ApplicationResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getCurrentApplication(): CwHttpResponse<ApplicationEntity?> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        return httpClient.submit(request).toEntity(ApplicationEntity.serializer())
    }
    
    suspend fun editCurrentApplication(request: ModifyCurrentApplicationRequestEntity): CwHttpResponse<ApplicationEntity?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/applications/@me") {
            jsonParams(request, ModifyCurrentApplicationRequestEntity.serializer())
        }
        return httpClient.submit(httpRequest).toEntity(ApplicationEntity.serializer())
    }
    
    // TODO: Make this testable in integration tests
    suspend fun getApplicationActivityInstance(
        applicationId: Snowflake, 
        instanceId: String
    ): CwHttpResponse<ApplicationActivityInstanceEntity?> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        return httpClient.submit(request).toEntity(ApplicationActivityInstanceEntity.serializer())
    }
    
}