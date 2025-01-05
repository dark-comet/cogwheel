package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.response.GetGatewayUrlResponseObject

class GatewayResource
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getGatewayUrl(): CwHttpResponse<GetGatewayUrlResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/gateway")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GetGatewayUrlResponseObject.serializer())
    }
    
}