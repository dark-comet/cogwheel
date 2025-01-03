package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.ApplicationRoleConnectionMetadataEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse

class ApplicationRoleConnectionMetadataResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getApplicationRoleConnectionMetadataRecords(): CwHttpResponse<List<ApplicationRoleConnectionMetadataEntity>> {
        TODO("To be implemented")
    }
    
    fun updateApplicationRoleConnectionMetadataRecords(): CwHttpResponse<List<ApplicationRoleConnectionMetadataEntity>> {
        TODO("To be implemented")
    }
    
}