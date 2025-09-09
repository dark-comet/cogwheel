@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.GuildResource
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.Channel
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.toModel
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.Guild
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildMember
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildPreview
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.request.*
import xyz.darkcomet.cogwheel.framework.models.response.ListActiveGuildThreadsResponse
import xyz.darkcomet.cogwheel.framework.models.response.toModel
import xyz.darkcomet.cogwheel.framework.primitives.*

class GuildApi
internal constructor(resource: GuildResource) {
    
    @JvmField val get = object : RequestInvocation1S<GuildId, GetGuildRequestSpec, Guild>() {
        
        override fun createRequest(p1: GuildId): GetGuildRequestSpec {
            return GetGuildRequestSpec(p1)
        }

        override suspend fun invoke(request: GetGuildRequestSpec): Response<Guild> {
            val response = resource.getGuild(request.guildId.snowflake, request.withCounts)
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField val getPreview = object : Invocation1<GuildId, GuildPreview>() {
        
        override suspend fun invoke(p1: GuildId): Response<GuildPreview> {
            val response = resource.getGuildPreview(p1.snowflake)
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
        
    }
    
    @JvmField val modify = object : RequestInvocation1S<GuildId, ModifyGuildRequestSpec, Guild>() {
        
        override fun createRequest(p1: GuildId): ModifyGuildRequestSpec {
            return ModifyGuildRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyGuildRequestSpec): Response<Guild> {
            val response = resource.modifyGuild(request.guildId.snowflake, request.buildParameters())
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField val getChannels = object : Invocation1<GuildId, List<Channel>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<Channel>> {
            val response = resource.getGuildChannels(p1.snowflake)
            val result = response.data?.map { it.toModel() }
            
            return Response(result, response)
        }

    }
    
    @JvmField val createChannel = object : RequestInvocation1S<GuildId, CreateGuildChannelRequestSpec, Channel>() {
        
        override fun createRequest(p1: GuildId): CreateGuildChannelRequestSpec {
            return CreateGuildChannelRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateGuildChannelRequestSpec): Response<Channel> {
            val response = resource.createGuildChannel(
                request.guildId.snowflake, 
                request.buildParameters()
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
        
    }
    
    @JvmField val modifyChannelPositions = object : RequestInvocation1S<GuildId, ModifyGuildChannelPositionsRequestSpec, Boolean>() {
        
        override fun createRequest(p1: GuildId): ModifyGuildChannelPositionsRequestSpec {
            return ModifyGuildChannelPositionsRequestSpec(p1)
        }

        override suspend fun invoke(request: ModifyGuildChannelPositionsRequestSpec): Response<Boolean> {
            val response = resource.modifyGuildChannelPositions(
                request.guildId.snowflake,
                request.modifiedPositions
            )
            val result = response.raw.success
            
            return Response(result, response)
        }

    }
    
    @JvmField val listActiveThreads = object : Invocation1<GuildId, ListActiveGuildThreadsResponse>() {
        
        override suspend fun invoke(p1: GuildId): Response<ListActiveGuildThreadsResponse> {
            val response = resource.listActiveGuildThreads(p1.snowflake)
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField val getMember = object : Invocation2<GuildId, UserId, GuildMember>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: UserId
        ): Response<GuildMember> {
            val response = resource.getGuildMember(p1.snowflake, p2.snowflake)
            val result = response.data?.toModel()
            
            return Response(result, response)
        }
        
    }
    
    @JvmField val listMembers = object : RequestInvocation1S<GuildId, ListGuildMembersRequestSpec, List<GuildMember>>() {
        
        override fun createRequest(p1: GuildId): ListGuildMembersRequestSpec {
            return ListGuildMembersRequestSpec(p1)
        }

        override suspend fun invoke(request: ListGuildMembersRequestSpec): Response<List<GuildMember>> {
            val response = resource.listGuildMembers(
                request.guildId.snowflake,
                request.limit,
                request.after
            )
            val result = response.data?.map { it.toModel() }
            
            return Response(result, response)
        }

    }
    
    @JvmField val searchMembers = object : RequestInvocation1S<GuildId, SearchGuildMembersRequestSpec, List<GuildMember>>() {
        
        override fun createRequest(p1: GuildId): SearchGuildMembersRequestSpec {
            return SearchGuildMembersRequestSpec(p1)
        }

        override suspend fun invoke(request: SearchGuildMembersRequestSpec): Response<List<GuildMember>> {
            val response = resource.searchGuildMembers(
                request.guildId.snowflake,
                request.query ?: throw InvalidModelException("'query' is required"),
                request.limit
            )
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField val addMember = object : RequestInvocation2S<GuildId, UserId, AddGuildMemberRequestSpec, GuildMember>() {
        
        override fun createRequest(
            p1: GuildId, 
            p2: UserId
        ): AddGuildMemberRequestSpec {
            return AddGuildMemberRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: AddGuildMemberRequestSpec): Response<GuildMember> {
            val response = resource.addGuildMember(
                request.guildId.snowflake,
                request.userId.snowflake,
                request.buildParameters()
            )
            val result = response.data?.toModel()
            
            return Response(result, response)
        }

    }
    
    @JvmField val modifyMember = object : RequestInvocation2S<GuildId, UserId, ModifyGuildMemberRequestSpec, GuildMember>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: UserId
        ): ModifyGuildMemberRequestSpec {
            return ModifyGuildMemberRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyGuildMemberRequestSpec): Response<GuildMember> {
            val response = resource.modifyGuildMember(
                request.guildId.snowflake,
                request.userId.snowflake,
                request.buildParameters()
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
}