package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.VoiceRegionEntity
import xyz.darkcomet.cogwheel.core.network.objects.VoiceStateEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class VoiceResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listVoiceRegions(): CwHttpResponse<List<VoiceRegionEntity>> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserVoiceState(guildId: Snowflake): CwHttpResponse<VoiceStateEntity> {
        TODO("To be implemented")
    }
    
    fun getUserVoiceState(guildId: Snowflake, userId: Snowflake): CwHttpResponse<VoiceStateEntity> {
        TODO("To be implemented")
    }
    
    fun modifyCurrentUserVoiceState(guildId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun modifyUserVoiceState(guildId: Snowflake, userId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
}