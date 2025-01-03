package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.SendSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.response.ListGuildSoundboardSoundsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SoundboardResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun sendSoundboardSound(
        channelId: Snowflake, 
        request: SendSoundboardSoundRequestParameters
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun listDefaultSoundboardSounds(): CwHttpResponse<List<SoundboardSoundObject>> {
        TODO("To be implemented")
    }
    
    fun listGuildSoundboardSounds(guildId: Snowflake): CwHttpResponse<ListGuildSoundboardSoundsResponseObject> {
        TODO("To be implemented")
    }
    
    fun getGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<SoundboardSoundObject> {
        TODO("To be implemented")
    }
    
    fun createGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<SoundboardSoundObject> {
        TODO("To be implemented")
    }
    
    fun modifyGuildSoundboardSound(
        guildId: Snowflake, 
        request: ModifyGuildSoundboardSoundRequestParameters
    ): CwHttpResponse<SoundboardSoundObject> {
        TODO("To be implemented")
    }
    
    fun deleteGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}