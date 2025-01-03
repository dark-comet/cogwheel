package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.StageInstanceObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyStageInstanceRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StageInstanceResource 
internal constructor(httpClient: CwHttpClient) {
    
    fun createStageInstance(request: CreateStageInstanceRequestParameters): CwHttpResponse<StageInstanceObject> {
        TODO("To be implemented")
    }
    
    fun getStageInstance(channelId: Snowflake): CwHttpResponse<StageInstanceObject> {
        TODO("To be implemented")
    }
    
    fun modifyStageInstance(
        channelId: Snowflake, 
        request: ModifyStageInstanceRequestParameters
    ): CwHttpResponse<StageInstanceObject> {
        TODO("To be implemented")
    }
    
    fun deleteStageInstance(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}