package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.CreateGuildScheduledEventRequestEntity
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntity
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventUserEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildScheduledEventResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listScheduledEventsForGuild(guildId: Snowflake): CwHttpResponse<List<GuildScheduledEventEntity>> {
        TODO("To be implemented")
    }
    
    fun createGuildScheduledEvent(
        guildId: Snowflake,
        request: CreateGuildScheduledEventRequestEntity,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventEntity> {
        TODO("To be implemented")
    }
    
    fun getGuildScheduledEvent(
        guildId: Snowflake, 
        eventId: Snowflake
    ): CwHttpResponse<GuildScheduledEventEntity> {
        TODO("To be implemented")
    }
    
    fun modifyGuildScheduledEvent(
        guildId: Snowflake,
        eventId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventEntity> {
        TODO("To be implemented")
    }
    
    fun deleteGuildScheduledEvent(
        guildId: Snowflake, 
        eventId: Snowflake
    ): CwHttpResponse<Unit> {
        TODO("To be implemented")
    }
    
    fun getGuildScheduledEventUsers(
        guildId: Snowflake, 
        eventId: Snowflake
    ): CwHttpResponse<List<GuildScheduledEventUserEntity>> {
        TODO("To be implemented")
    }
    
}