package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.SkuObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SkuResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listSkus(applicationId: Snowflake): CwHttpResponse<List<SkuObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/application/${applicationId}/skus")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(SkuObject.serializer()))
    }
    
}