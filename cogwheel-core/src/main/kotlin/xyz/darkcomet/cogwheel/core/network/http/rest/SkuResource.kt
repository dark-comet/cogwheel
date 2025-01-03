package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.SkuEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SkuResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listSkus(applicationId: Snowflake): CwHttpResponse<List<SkuEntity>> {
        TODO("To be implemented")
    }
    
}