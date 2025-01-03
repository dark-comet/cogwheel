package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.entities.response.GetGatewayUrlResponseEntity

class GatewayResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getGatewayUrl(): CwHttpResponse<GetGatewayUrlResponseEntity> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/gateway")
        return httpClient.submit(request).toEntity(GetGatewayUrlResponseEntity.serializer())
    }
    
}