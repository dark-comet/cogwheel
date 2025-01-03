package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildSoundboardSoundRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.SendSoundboardSoundRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.response.ListGuildSoundboardSoundsResponseEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SoundboardResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun sendSoundboardSound(
        channelId: Snowflake, 
        request: SendSoundboardSoundRequestEntity
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun listDefaultSoundboardSounds(): CwHttpResponse<List<SoundboardSoundEntity>> {
        TODO("To be implemented")
    }
    
    fun listGuildSoundboardSounds(guildId: Snowflake): CwHttpResponse<ListGuildSoundboardSoundsResponseEntity> {
        TODO("To be implemented")
    }
    
    fun getGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<SoundboardSoundEntity> {
        TODO("To be implemented")
    }
    
    fun createGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<SoundboardSoundEntity> {
        TODO("To be implemented")
    }
    
    fun modifyGuildSoundboardSound(
        guildId: Snowflake, 
        request: ModifyGuildSoundboardSoundRequestEntity
    ): CwHttpResponse<SoundboardSoundEntity> {
        TODO("To be implemented")
    }
    
    fun deleteGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
}