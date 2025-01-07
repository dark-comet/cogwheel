package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildTemplateObject
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildFromGuildTemplateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildTemplateRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildTemplateRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildTemplateResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getGuildTemplate(templateCode: String): CwHttpResponse<GuildTemplateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/templates/${templateCode}")
        val response = httpClient.submit(httpRequest)
        
        return response.withData(GuildTemplateObject.serializer())
    }
    
    suspend fun createGuildFromGuildTemplate(
        templateCode: String,
        request: CreateGuildFromGuildTemplateRequestParameters
    ): CwHttpResponse<GuildObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/templates/${templateCode}") {
            jsonParams(request, CreateGuildFromGuildTemplateRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildObject.serializer())
    }
    
    suspend fun getGuildTemplates(guildId: Snowflake): CwHttpResponse<List<GuildTemplateObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/guilds/${guildId}/templates")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildTemplateObject.serializer()))
    }
    
    suspend fun createGuildTemplate(
        guildId: Snowflake,
        request: CreateGuildTemplateRequestParameters
    ): CwHttpResponse<GuildTemplateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/guilds/${guildId}/templates") {
            jsonParams(request, CreateGuildTemplateRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildTemplateObject.serializer())
    }
    
    suspend fun syncGuildTemplate(
        guildId: Snowflake, 
        templateCode: String
    ): CwHttpResponse<GuildTemplateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/guilds/${guildId}/templates/${templateCode}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildTemplateObject.serializer())
    }
    
    suspend fun modifyGuildTemplate(
        guildId: Snowflake, 
        templateCode: String,
        request: ModifyGuildTemplateRequestParameters
    ): CwHttpResponse<GuildTemplateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/guilds/${guildId}/templates/${templateCode}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildTemplateObject.serializer())
    }
    
    suspend fun deleteGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/guilds/${guildId}/templates/${templateCode}")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildTemplateObject.serializer())
    }
}