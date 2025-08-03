@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.ApplicationRoleConnectionMetadataResource
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.ApplicationRoleConnectionMetadata
import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.toModel
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Invocation2
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future

class ApplicationRoleConnectionMetadataApi
internal constructor(resource: ApplicationRoleConnectionMetadataResource) {
    
    @JvmField
    val getRecords = GetRecordsEndpoint(resource)
    
    @JvmField
    val updateRecords = UpdateRecordsEndpoint(resource)
    
}

class GetRecordsEndpoint
internal constructor(private val resource: ApplicationRoleConnectionMetadataResource)
    : Invocation1<ApplicationId, List<ApplicationRoleConnectionMetadata>>() {

    override suspend fun invoke(id: ApplicationId): Response<List<ApplicationRoleConnectionMetadata>> {
        val response = resource.getApplicationRoleConnectionMetadataRecords(id.snowflake);
        val result = response.data?.map { it.toModel() }

        return Response(result, response)
    }

    override fun async(id: ApplicationId): Future<Response<List<ApplicationRoleConnectionMetadata>>> {
        return super.async(id)
    }

    override fun block(id: ApplicationId): Response<List<ApplicationRoleConnectionMetadata>> {
        return super.block(id)
    }
}

class UpdateRecordsEndpoint
internal constructor(private val resource: ApplicationRoleConnectionMetadataResource) 
    : Invocation2<ApplicationId, List<ApplicationRoleConnectionMetadata>, List<ApplicationRoleConnectionMetadata>>() {

    override suspend fun invoke(
        id: ApplicationId,
        newMetadata: List<ApplicationRoleConnectionMetadata>
    ): Response<List<ApplicationRoleConnectionMetadata>> {
        val applicationId = id.snowflake
        val newRecords = newMetadata.map { it.toObject() }

        val response = resource.updateApplicationRoleConnectionMetadataRecords(applicationId, newRecords);
        val result = response.data?.map { it.toModel() }

        return Response(result, response)
    }

    override fun async(
        id: ApplicationId,
        newMetadata: List<ApplicationRoleConnectionMetadata>
    ): Future<Response<List<ApplicationRoleConnectionMetadata>>> {
        return super.async(id, newMetadata)
    }

    override fun block(
        id: ApplicationId,
        newMetadata: List<ApplicationRoleConnectionMetadata>
    ): Response<List<ApplicationRoleConnectionMetadata>> {
        return super.block(id, newMetadata)
    }
}