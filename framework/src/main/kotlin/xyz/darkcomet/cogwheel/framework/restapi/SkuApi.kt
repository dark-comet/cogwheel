@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.SkuResource
import xyz.darkcomet.cogwheel.framework.models.entitles.application.Sku
import xyz.darkcomet.cogwheel.framework.models.entitles.application.toModel
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.Invocation1
import xyz.darkcomet.cogwheel.framework.primitives.Response

class SkuApi
internal constructor(resource: SkuResource) {
    
    @JvmField
    val list = object : Invocation1<ApplicationId, List<Sku>>() {
        
        override suspend fun invoke(p1: ApplicationId): Response<List<Sku>> {
            val response = resource.listSkus(p1.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }

}