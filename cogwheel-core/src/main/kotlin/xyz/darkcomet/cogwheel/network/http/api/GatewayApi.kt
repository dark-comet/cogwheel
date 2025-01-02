package xyz.darkcomet.cogwheel.network.http.api

import xyz.darkcomet.cogwheel.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.network.entities.response.GetGatewayResponseEntity

class GatewayApi
internal constructor(private val httpClient: CwHttpClient) {
    suspend fun get(): CwHttpResponse<GetGatewayResponseEntity> {
        val request = CwHttpRequest.create(CwHttpMethod.GET, "/gateway")
        return httpClient.submit(request).toEntity(GetGatewayResponseEntity.serializer())
    }
}