package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventUserObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.network.objects.request.CreateGuildScheduledEventRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildScheduledEventResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun listScheduledEventsForGuild(guildId: Snowflake): CwHttpResponse<List<GuildScheduledEventObject>> {
        TODO("To be implemented")
    }
    
    fun createGuildScheduledEvent(
        guildId: Snowflake,
        request: CreateGuildScheduledEventRequestParameters,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventObject> {
        TODO("To be implemented")
    }
    
    fun getGuildScheduledEvent(
        guildId: Snowflake, 
        eventId: Snowflake
    ): CwHttpResponse<GuildScheduledEventObject> {
        TODO("To be implemented")
    }
    
    fun modifyGuildScheduledEvent(
        guildId: Snowflake,
        eventId: Snowflake,
        auditLogReason: String? = null
    ): CwHttpResponse<GuildScheduledEventObject> {
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
    ): CwHttpResponse<List<GuildScheduledEventUserObject>> {
        TODO("To be implemented")
    }
    
}