package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGroupDmRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateUserDmRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentUserRequestParameters
import xyz.darkcomet.cogwheel.core.network.objects.request.UpdateCurrentUserApplicationRoleConnectionRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class UserResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getCurrentUser(): CwHttpResponse<UserObject> {
        TODO("To be implemented")
    }
    
    fun getUser(userId: Snowflake): CwHttpResponse<UserObject> {
        TODO("To be implemented")
    }
    
    fun modifyCurrentUser(request: ModifyCurrentUserRequestParameters): CwHttpResponse<UserObject> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserGuilds(): CwHttpResponse<List<GuildObject>> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserGuildMember(guildId: Snowflake): CwHttpResponse<GuildMemberObject> {
        TODO("To be implemented")
    }
    
    fun leaveGuild(guildId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun createDm(request: CreateUserDmRequestParameters): CwHttpResponse<ChannelObject> {
        TODO("To be implemented")
    }
    
    fun createGroupDm(request: CreateGroupDmRequestParameters): CwHttpResponse<ChannelObject> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserConnections(): CwHttpResponse<ConnectionObject> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserApplicationRoleConnection(applicationId: Snowflake): CwHttpResponse<UserApplicationRoleConnectionObject> {
        TODO("To be implemented")
    }
    
    fun updateCurrentUserApplicationRoleConnection(
        applicationId: Snowflake, 
        request: UpdateCurrentUserApplicationRoleConnectionRequestParameters
    ): CwHttpResponse<UserApplicationRoleConnectionObject> {
        TODO("To be implemented")
    }
    
}