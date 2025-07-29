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
import java.util.concurrent.Future
import java.util.function.Consumer

class GuildScheduledEventApi
internal constructor(resource: GuildScheduledEventResource) {
    
    @JvmField
    val list = ListGuildScheduledEventsEndpoint(resource)
    
    @JvmField
    val create = CreateGuildScheduledEventEndpoint(resource)
    
    @JvmField
    val get = GetGuildScheduledEventEndpoint(resource)
    
    @JvmField
    val modify = ModifyGuildScheduledEventEndpoint(resource)
    
    @JvmField
    val delete = DeleteGuildScheduledEventEndpoint(resource)
    
    @JvmField
    val getEventUsers = GetGuildScheduledEventUsersEndpoint(resource)
    
}

class ListGuildScheduledEventsEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : Invocation1<GuildId, List<GuildScheduledEvent>>() {
        
    override suspend fun invoke(guildId: GuildId): Response<List<GuildScheduledEvent>> {
        val response = resource.listScheduledEventsForGuild(guildId.snowflake)
        val result = response.data?.map { it.toModel() }
        
        return Response(result, response)
    }

    override fun async(guildId: GuildId): Future<Response<List<GuildScheduledEvent>>> {
        return super.async(guildId)
    }

    override fun block(guildId: GuildId): Response<List<GuildScheduledEvent>> {
        return super.block(guildId)
    }
}

class CreateGuildScheduledEventEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : RequestInvocation1S<GuildId, CreateGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
    override fun createRequest(guildId: GuildId): CreateGuildScheduledEventRequestSpec {
        return CreateGuildScheduledEventRequestSpec(guildId)
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

    override suspend fun invoke(
        guildId: GuildId,
        request: CreateGuildScheduledEventRequestSpec?,
        config: (CreateGuildScheduledEventRequestSpec.() -> Unit)?
    ): Response<GuildScheduledEvent> {
        return super.invoke(guildId, request, config)
    }

    override fun async(
        guildId: GuildId,
        config: Consumer<CreateGuildScheduledEventRequestSpec>?
    ): Future<Response<GuildScheduledEvent>> {
        return super.async(guildId, config)
    }

    override fun block(
        guildId: GuildId,
        config: Consumer<CreateGuildScheduledEventRequestSpec>?
    ): Response<GuildScheduledEvent> {
        return super.block(guildId, config)
    }
}

class GetGuildScheduledEventEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : RequestInvocation2S<GuildId, GuildScheduledEventId, GetGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
    override fun createRequest(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): GetGuildScheduledEventRequestSpec {
        return GetGuildScheduledEventRequestSpec(guildId, eventId)
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

    override fun async(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<GetGuildScheduledEventRequestSpec>?
    ): Future<Response<GuildScheduledEvent>> {
        return super.async(guildId, eventId, config)
    }

    override fun block(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<GetGuildScheduledEventRequestSpec>?
    ): Response<GuildScheduledEvent> {
        return super.block(guildId, eventId, config)
    }
}

class ModifyGuildScheduledEventEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : RequestInvocation2S<GuildId, GuildScheduledEventId, ModifyGuildScheduledEventRequestSpec, GuildScheduledEvent>() {
        
    override fun createRequest(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): ModifyGuildScheduledEventRequestSpec {
        return ModifyGuildScheduledEventRequestSpec(guildId, eventId)
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

    override fun async(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<ModifyGuildScheduledEventRequestSpec>?
    ): Future<Response<GuildScheduledEvent>> {
        return super.async(guildId, eventId, config)
    }

    override fun block(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<ModifyGuildScheduledEventRequestSpec>?
    ): Response<GuildScheduledEvent> {
        return super.block(guildId, eventId, config)
    }
}

class DeleteGuildScheduledEventEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : Invocation2<GuildId, GuildScheduledEventId, Boolean>() {
        
    override suspend fun invoke(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): Response<Boolean> {
        val response = resource.deleteGuildScheduledEvent(guildId.snowflake, eventId.snowflake)
        val result = response.raw.success
        
        return Response(result, response)
    }

    override fun async(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): Future<Response<Boolean>> {
        return super.async(guildId, eventId)
    }

    override fun block(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): Response<Boolean> {
        return super.block(guildId, eventId)
    }
}

class GetGuildScheduledEventUsersEndpoint
internal constructor(private val resource: GuildScheduledEventResource)
    : RequestInvocation2S<GuildId, GuildScheduledEventId, GetGuildScheduledEventUsersRequestSpec, List<GuildScheduledEventUser>>() {
        
    override fun createRequest(
        guildId: GuildId,
        eventId: GuildScheduledEventId
    ): GetGuildScheduledEventUsersRequestSpec {
        return GetGuildScheduledEventUsersRequestSpec(guildId, eventId)
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

    override fun async(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<GetGuildScheduledEventUsersRequestSpec>?
    ): Future<Response<List<GuildScheduledEventUser>>> {
        return super.async(guildId, eventId, config)
    }

    override fun block(
        guildId: GuildId,
        eventId: GuildScheduledEventId,
        config: Consumer<GetGuildScheduledEventUsersRequestSpec>?
    ): Response<List<GuildScheduledEventUser>> {
        return super.block(guildId, eventId, config)
    }
}