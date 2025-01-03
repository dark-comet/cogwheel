package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.entities.StageInstanceEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.CreateStageInstanceRequestEntity
import xyz.darkcomet.cogwheel.core.network.entities.request.ModifyStageInstanceRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class StageInstanceResource 
internal constructor(httpClient: CwHttpClient) {
    
    fun createStageInstance(request: CreateStageInstanceRequestEntity): CwHttpResponse<StageInstanceEntity> {
        TODO("To be implemented")
    }
    
    fun getStageInstance(channelId: Snowflake): CwHttpResponse<StageInstanceEntity> {
        TODO("To be implemented")
    }
    
    fun modifyStageInstance(
        channelId: Snowflake, 
        request: ModifyStageInstanceRequestEntity
    ): CwHttpResponse<StageInstanceEntity> {
        TODO("To be implemented")
    }
    
    fun deleteStageInstance(channelId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}