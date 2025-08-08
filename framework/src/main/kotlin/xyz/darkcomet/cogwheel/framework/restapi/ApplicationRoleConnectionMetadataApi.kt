@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationRoleConnectionMetadataResource
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.ApplicationRoleConnectionMetadata
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.toModel
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.Response

class ApplicationRoleConnectionMetadataApi
internal constructor(resource: ApplicationRoleConnectionMetadataResource) {
    
    @JvmField
    val getRecords = object : Invocation1<ApplicationId, List<ApplicationRoleConnectionMetadata>>() {
        
        override suspend fun invoke(p1: ApplicationId): Response<List<ApplicationRoleConnectionMetadata>> {
            val response = resource.getApplicationRoleConnectionMetadataRecords(p1.snowflake);
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }
        
    }
    
    // TODO: Convert p2 to RequestSpec
    @JvmField
    val updateRecords = object : Invocation2<ApplicationId, List<ApplicationRoleConnectionMetadata>, List<ApplicationRoleConnectionMetadata>>() {
        
        override suspend fun invoke(
            p1: ApplicationId,
            p2: List<ApplicationRoleConnectionMetadata>
        ): Response<List<ApplicationRoleConnectionMetadata>> {
            val applicationId = p1.snowflake
            val newRecords = p2.map { it.toObject() }

            val response = resource.updateApplicationRoleConnectionMetadataRecords(applicationId, newRecords);
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }
        
    }
    
}