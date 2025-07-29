@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.GatewayResource
import xyz.darkcomet.cogwheel.framework.primitives.Invocation0
import xyz.darkcomet.cogwheel.framework.primitives.Response

class GatewayApi
internal constructor(resource: GatewayResource) {
    
    @JvmField
    val getUrl = GetGatewayUrlEndpoint(resource)
    
}

class GetGatewayUrlEndpoint
internal constructor(private val resource: GatewayResource) 
    : Invocation0<String>() {
        
    override suspend fun invoke(): Response<String> {
        val response = resource.getGatewayUrl()
        val result = response.data?.url
        
        return Response(result, response)
    }
    
}