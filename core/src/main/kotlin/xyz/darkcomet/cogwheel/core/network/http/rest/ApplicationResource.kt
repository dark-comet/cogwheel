package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.GET
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.PATCH
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationActivityInstanceObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.core.network.objects.EditCurrentApplicationRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ApplicationResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getCurrentApplication(): CwHttpResponse<ApplicationObject?> {
        val httpRequest = CwHttpRequest.create(GET, "/applications/@me")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationObject.serializer())
    }
    
    suspend fun editCurrentApplication(request: EditCurrentApplicationRequestParameters): CwHttpResponse<ApplicationObject?> {
        val httpRequest = CwHttpRequest.create(PATCH, "/applications/@me") {
            jsonParams(request, EditCurrentApplicationRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationObject.serializer())
    }
    
    // TODO: Make this testable in integration tests
    suspend fun getApplicationActivityInstance(
        applicationId: Snowflake, 
        instanceId: String
    ): CwHttpResponse<ApplicationActivityInstanceObject?> {
        val httpRequest = CwHttpRequest.create(GET, "/applications/${applicationId}/activity-instances/${instanceId}")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationActivityInstanceObject.serializer())
    }
    
}