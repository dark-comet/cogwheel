package xyz.darkcomet.cogwheel.core.network.http.rest

import kotlinx.serialization.builtins.ListSerializer
import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGroupDmRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateUserDmRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentUserRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.UpdateCurrentUserApplicationRoleConnectionRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class UserResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    suspend fun getCurrentUser(): CwHttpResponse<UserObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/@me")
        val response = httpClient.submit(httpRequest)

        return response.withData(UserObject.serializer())
    }
    
    suspend fun getUser(userId: Snowflake): CwHttpResponse<UserObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/${userId}")
        val response = httpClient.submit(httpRequest)

        return response.withData(UserObject.serializer())
    }
    
    suspend fun modifyCurrentUser(request: ModifyCurrentUserRequestParameters): CwHttpResponse<UserObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PATCH, "/users/@me") {
            jsonParams(request, ModifyCurrentUserRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(UserObject.serializer())
    }
    
    suspend fun getCurrentUserGuilds(): CwHttpResponse<List<GuildObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/@me/guilds")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(GuildObject.serializer()))
    }
    
    suspend fun getCurrentUserGuildMember(guildId: Snowflake): CwHttpResponse<GuildMemberObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/@me/guilds/${guildId}/member")
        val response = httpClient.submit(httpRequest)

        return response.withData(GuildMemberObject.serializer())
    }
    
    suspend fun leaveGuild(guildId: Snowflake): CwHttpResponse<Unit> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.DELETE, "/users/@me/guilds/${guildId}")
        val response = httpClient.submit(httpRequest)

        return response.withNoData()
    }
    
    suspend fun createDm(request: CreateUserDmRequestParameters): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/users/@me/channels") {
            jsonParams(request, CreateUserDmRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun createGroupDm(request: CreateGroupDmRequestParameters): CwHttpResponse<ChannelObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.POST, "/users/@me/channels") {
            jsonParams(request, CreateGroupDmRequestParameters.serializer())
        }
        val response = httpClient.submit(httpRequest)

        return response.withData(ChannelObject.serializer())
    }
    
    suspend fun getCurrentUserConnections(): CwHttpResponse<List<UserConnectionObject>> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/@me/connections")
        val response = httpClient.submit(httpRequest)

        return response.withData(ListSerializer(UserConnectionObject.serializer()))
    }
    
    suspend fun getCurrentUserApplicationRoleConnection(applicationId: Snowflake): CwHttpResponse<UserApplicationRoleConnectionObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.GET, "/users/@me/applications/${applicationId}/role-connection")
        val response = httpClient.submit(httpRequest)

        return response.withData(UserApplicationRoleConnectionObject.serializer())
    }
    
    suspend fun updateCurrentUserApplicationRoleConnection(
        applicationId: Snowflake, 
        request: UpdateCurrentUserApplicationRoleConnectionRequestParameters
    ): CwHttpResponse<UserApplicationRoleConnectionObject> {
        val httpRequest = CwHttpRequest.create(CwHttpMethod.PUT, "/users/@me/applications/${applicationId}/role-connection")
        val response = httpClient.submit(httpRequest)

        return response.withData(UserApplicationRoleConnectionObject.serializer())
    }
    
}