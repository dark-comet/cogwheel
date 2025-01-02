package xyz.darkcomet.cogwheel.core.network.http.api

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.entities.response.GetGatewayResponseEntity

class GatewayApi
internal constructor(private val httpClient: CwHttpClient) {
    suspend fun get(): CwHttpResponse<GetGatewayResponseEntity> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/gateway")
        return httpClient.submit(request).toEntity(GetGatewayResponseEntity.serializer())
    }
}