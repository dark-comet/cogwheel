package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.SkuEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SkuResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun list(applicationId: Snowflake): CwHttpResponse<List<SkuEntity>> {
        TODO("To be implemented")
    }
    
}