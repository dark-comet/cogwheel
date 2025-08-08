@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE", "RedundantOverride", "unused")

package xyz.darkcomet.cogwheel.framework.restapi

import xyz.darkcomet.cogwheel.core.network.http.rest.GuildScheduledEventResource
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEvent
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildScheduledEventUser
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.request.CreateGuildScheduledEventRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.GetGuildScheduledEventRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.GetGuildScheduledEventUsersRequestSpec
import xyz.darkcomet.cogwheel.framework.models.request.ModifyGuildScheduledEventRequestSpec
import xyz.darkcomet.cogwheel.framework.primitives.*

class GuildScheduledEventApi
internal constructor(resource: GuildScheduledEventResource) {
    
    @JvmField
    val list = object : Invocation1<GuildId, List<GuildScheduledEvent>>() {
        
        override suspend fun invoke(p1: GuildId): Response<List<GuildScheduledEvent>> {
            val response = resource.listScheduledEventsForGuild(p1.snowflake)
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
    @JvmField
    val create = object : RequestInvocation1S<GuildId, CreateGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
        override fun createRequest(p1: GuildId): CreateGuildScheduledEventRequestSpec {
            return CreateGuildScheduledEventRequestSpec(p1)
        }

        override suspend fun invoke(request: CreateGuildScheduledEventRequestSpec): Response<GuildScheduledEvent> {
            val response = resource.createGuildScheduledEvent(
                request.guildId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val get = object : RequestInvocation2S<GuildId, GuildScheduledEventId, GetGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: GuildScheduledEventId
        ): GetGuildScheduledEventRequestSpec {
            return GetGuildScheduledEventRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: GetGuildScheduledEventRequestSpec): Response<GuildScheduledEvent> {
            val response = resource.getGuildScheduledEvent(
                request.guildId.snowflake,
                request.eventId.snowflake,
                request.withUserCount
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }
        
    }
        
    
    @JvmField
    val modify = object : RequestInvocation2S<GuildId, GuildScheduledEventId, ModifyGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: GuildScheduledEventId
        ): ModifyGuildScheduledEventRequestSpec {
            return ModifyGuildScheduledEventRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: ModifyGuildScheduledEventRequestSpec): Response<GuildScheduledEvent> {
            val response = resource.modifyGuildScheduledEvent(
                request.guildId.snowflake,
                request.eventId.snowflake,
                request.buildParameters(),
                request.auditLogReason
            )
            val result = response.data?.toModel()

            return Response(result, response)
        }

    }
    
    @JvmField
    val delete = object : Invocation2<GuildId, GuildScheduledEventId, Boolean>() {
        
        override suspend fun invoke(
            p1: GuildId,
            p2: GuildScheduledEventId
        ): Response<Boolean> {
            val response = resource.deleteGuildScheduledEvent(p1.snowflake, p2.snowflake)
            val result = response.raw.success

            return Response(result, response)
        }
        
    }
    
    @JvmField
    val getEventUsers = object : RequestInvocation2S<GuildId, GuildScheduledEventId, GetGuildScheduledEventUsersRequestSpec, List<GuildScheduledEventUser>>() {
        
        override fun createRequest(
            p1: GuildId,
            p2: GuildScheduledEventId
        ): GetGuildScheduledEventUsersRequestSpec {
            
            return GetGuildScheduledEventUsersRequestSpec(p1, p2)
        }

        override suspend fun invoke(request: GetGuildScheduledEventUsersRequestSpec): Response<List<GuildScheduledEventUser>> {
            val response = resource.getGuildScheduledEventUsers(
                request.guildId.snowflake,
                request.eventId.snowflake,
                request.limit,
                request.withMember,
                request.before,
                request.after
            )
            val result = response.data?.map { it.toModel() }

            return Response(result, response)
        }

    }
    
}