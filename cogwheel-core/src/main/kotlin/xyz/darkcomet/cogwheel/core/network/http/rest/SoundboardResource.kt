package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.SoundboardSoundObject
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyGuildSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.SendSoundboardSoundRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.response.ListGuildSoundboardSoundsResponseObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class SoundboardResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun sendSoundboardSound(
        channelId: Snowflake, 
        request: SendSoundboardSoundRequestParameters
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/channels/${channelId}/send-soundboard-sound") {
            jsonParams(request, SendSoundboardSoundRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)
        
        return response.withNoData()
    }
    
    suspend fun listDefaultSoundboardSounds(): CwHttpResponse<List<SoundboardSoundObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/soundboard-default-sounds")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(SoundboardSoundObject.serializer()))
    }
    
    suspend fun listGuildSoundboardSounds(guildId: Snowflake): CwHttpResponse<ListGuildSoundboardSoundsResponseObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guild/${guildId}/soundboard-sounds")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListGuildSoundboardSoundsResponseObject.serializer())
    }
    
    suspend fun getGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake
    ): CwHttpResponse<SoundboardSoundObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guild/${guildId}/soundboard-sounds/${soundId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(SoundboardSoundObject.serializer())
    }
    
    suspend fun createGuildSoundboardSound(
        guildId: Snowflake,
        request: CreateGuildSoundboardSoundRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<SoundboardSoundObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guild/${guildId}/soundboard-sounds") {
            jsonParams(request, CreateGuildSoundboardSoundRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(SoundboardSoundObject.serializer())
    }
    
    suspend fun modifyGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake,
        request: ModifyGuildSoundboardSoundRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<SoundboardSoundObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guild/${guildId}/soundboard-sounds/${soundId}") {
            jsonParams(request, ModifyGuildSoundboardSoundRequestParameters.serializer())
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(SoundboardSoundObject.serializer())
    }
    
    suspend fun deleteGuildSoundboardSound(
        guildId: Snowflake, 
        soundId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guild/${guildId}/soundboard-sounds/${soundId}") {
            optionalAuditLogReason(auditLogReason)
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}