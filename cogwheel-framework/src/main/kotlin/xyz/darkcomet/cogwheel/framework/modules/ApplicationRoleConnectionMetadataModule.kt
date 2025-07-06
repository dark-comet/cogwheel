package xyz.darkcomet.cogwheel.framework.modules

import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationRoleConnectionMetadataResource
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.ApplicationRoleConnectionMetadata
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.toModel
import xyz.darkcomet.cogwheel.framework.models.request.arcmetadata.UpdateApplicationRoleConnectionMetadataRecordsSpec
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.Response

class ApplicationRoleConnectionMetadataModule
internal constructor(private val resource: ApplicationRoleConnectionMetadataResource) {
    
    @JvmField
    val getRecords = GetRecordsEndpoint()
    
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE") // intentional rename
    inner class GetRecordsEndpoint() : GetEndpoint1<ApplicationId, List<ApplicationRoleConnectionMetadata>> {
            
        override suspend fun invoke(id: ApplicationId): Response<List<ApplicationRoleConnectionMetadata>> {
            val response = resource.getApplicationRoleConnectionMetadataRecords(id.snowflake);
            val result = response.data?.map { it.toModel() }
            
            return Response(result, response)
        }
    }
    
    @JvmField
    val updateRecords = object : RequestEndpoint<UpdateApplicationRoleConnectionMetadataRecordsSpec, List<ApplicationRoleConnectionMetadata>> {
        
        override fun createRequest(): UpdateApplicationRoleConnectionMetadataRecordsSpec {
            return UpdateApplicationRoleConnectionMetadataRecordsSpec()
        }

        override suspend fun invoke(requestSpec: UpdateApplicationRoleConnectionMetadataRecordsSpec): Response<List<ApplicationRoleConnectionMetadata>> {
            val appId = requestSpec.id?.snowflake ?: throw InvalidModelException("Required") // TODO
            val newRecords = requestSpec.metadataRecords?.map { it.toObject() } ?: throw InvalidModelException("Required") // TODO
            
            val response = resource.updateApplicationRoleConnectionMetadataRecords(appId, newRecords);
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }
    }
}