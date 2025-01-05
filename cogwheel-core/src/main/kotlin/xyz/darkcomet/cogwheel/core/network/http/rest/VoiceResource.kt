package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.objects.VoiceRegionObject
import xyz.darkcomet.cogwheel.core.network.objects.VoiceStateObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentUserVoiceStateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyUserVoiceStateRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class VoiceResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun listVoiceRegions(): CwHttpResponse<List<VoiceRegionObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/voice/regions")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(VoiceRegionObject.serializer()))
    }
    
    suspend fun getCurrentUserVoiceState(guildId: Snowflake): CwHttpResponse<VoiceStateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/voice-states/@me")
        val response = httpClient.submit(httpRequest)

        return response.withData(VoiceStateObject.serializer())
    }
    
    suspend fun getUserVoiceState(
        guildId: Snowflake, 
        userId: Snowflake
    ): CwHttpResponse<VoiceStateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/voice-states/${userId}")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(VoiceStateObject.serializer())
    }
    
    suspend fun modifyCurrentUserVoiceState(
        guildId: Snowflake,
        request: ModifyCurrentUserVoiceStateRequestParameters
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/voice-states/@me") {
            jsonParams(request, ModifyCurrentUserVoiceStateRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun modifyUserVoiceState(
        guildId: Snowflake, 
        userId: Snowflake,
        request: ModifyUserVoiceStateRequestParameters
    ): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/voice-states/${userId}") {
            jsonParams(request, ModifyUserVoiceStateRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
}