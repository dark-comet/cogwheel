package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.*
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGroupDmRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateUserDmRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.ModifyCurrentUserRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.request.UpdateCurrentUserApplicationRoleConnectionRequestEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class UserResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getCurrentUser(): CwHttpResponse<UserEntity> {
        TODO("To be implemented")
    }
    
    fun getUser(userId: Snowflake): CwHttpResponse<UserEntity> {
        TODO("To be implemented")
    }
    
    fun modifyCurrentUser(request: ModifyCurrentUserRequestEntity): CwHttpResponse<UserEntity> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserGuilds(): CwHttpResponse<List<GuildEntity>> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserGuildMember(guildId: Snowflake): CwHttpResponse<GuildMemberEntity> {
        TODO("To be implemented")
    }
    
    fun leaveGuild(guildId: Snowflake): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun createDm(request: CreateUserDmRequestEntity): CwHttpResponse<ChannelEntity> {
        TODO("To be implemented")
    }
    
    fun createGroupDm(request: CreateGroupDmRequestEntity): CwHttpResponse<ChannelEntity> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserConnections(): CwHttpResponse<ConnectionEntity> {
        TODO("To be implemented")
    }
    
    fun getCurrentUserApplicationRoleConnection(applicationId: Snowflake): CwHttpResponse<UserApplicationRoleConnectionEntity> {
        TODO("To be implemented")
    }
    
    fun updateCurrentUserApplicationRoleConnection(
        applicationId: Snowflake, 
        request: UpdateCurrentUserApplicationRoleConnectionRequestEntity
    ): CwHttpResponse<UserApplicationRoleConnectionEntity> {
        TODO("To be implemented")
    }
    
}