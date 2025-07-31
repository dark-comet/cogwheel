@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.SkuResource
import xyz.darkcomet.cogwheel.framework.models.entitles.application.Sku
import xyz.darkcomet.cogwheel.framework.models.entitles.application.toModel
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future

class SkuApi
internal constructor(resource: SkuResource) {
    
    @JvmField
    val list = ListSkusEndpoint(resource)
    
}

class ListSkusEndpoint 
internal constructor(private val resource: SkuResource)
    : Invocation1<ApplicationId, List<Sku>>() {
        
    override suspend fun invoke(applicationId: ApplicationId): Response<List<Sku>> {
        val response = resource.listSkus(applicationId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(applicationId: ApplicationId): Future<Response<List<Sku>>> {
        return super.async(applicationId)
    }

    override fun block(applicationId: ApplicationId): Response<List<Sku>> {
        return super.block(applicationId)
    }
}