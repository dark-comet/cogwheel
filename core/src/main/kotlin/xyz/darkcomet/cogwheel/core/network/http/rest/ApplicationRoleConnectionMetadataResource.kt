package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.GET
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod.PUT
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationRoleConnectionMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class ApplicationRoleConnectionMetadataResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getApplicationRoleConnectionMetadataRecords(
        applicationId: Snowflake
    ): CwHttpResponse<List<ApplicationRoleConnectionMetadataObject>> {
        val httpRequest = CwHttpRequest.create(GET, "/applications/${applicationId}/role-connections/metadata")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ListSerializer(ApplicationRoleConnectionMetadataObject.serializer()))
    }
    
    suspend fun updateApplicationRoleConnectionMetadataRecords(
        applicationId: Snowflake,
        request: List<ApplicationRoleConnectionMetadataObject>
    ): CwHttpResponse<List<ApplicationRoleConnectionMetadataObject>> {
        val httpRequest = CwHttpRequest.create(PUT, "/applications/${applicationId}/role-connections/metadata") {
            jsonParams(request, ListSerializer(ApplicationRoleConnectionMetadataObject.serializer()))
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withData(ListSerializer(ApplicationRoleConnectionMetadataObject.serializer()))
    }
    
}