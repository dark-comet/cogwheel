package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationActivityInstanceObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentApplicationRequestParameters

class ApplicationResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getCurrentApplication(): CwHttpResponse<ApplicationObject?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationObject.serializer())
    }
    
    suspend fun editCurrentApplication(request: ModifyCurrentApplicationRequestParameters): CwHttpResponse<ApplicationObject?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/applications/@me") {
            jsonParams(request, ModifyCurrentApplicationRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationObject.serializer())
    }
    
    // TODO: Make this testable in integration tests
    suspend fun getApplicationActivityInstance(
        applicationId: Snowflake, 
        instanceId: String
    ): CwHttpResponse<ApplicationActivityInstanceObject?> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/applications/@me")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ApplicationActivityInstanceObject.serializer())
    }
    
}