package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildTemplateObject
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildTemplateResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getGuildTemplate(templateCode: String): CwHttpResponse<GuildTemplateObject> {
        TODO("To be implemented")
    }
    
    fun createGuildFromGuildTemplate(templateCode: String): CwHttpResponse<GuildObject> {
        TODO("To be implemented")
    }
    
    fun getGuildTemplates(guildId: Snowflake): CwHttpResponse<List<GuildTemplateObject>> {
        TODO("To be implemented")
    }
    
    fun createGuildTemplate(guildId: Snowflake): CwHttpResponse<GuildTemplateObject> {
        TODO("To be implemented")
    }
    
    fun syncGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateObject> {
        TODO("To be implemented")
    }
    
    fun modifyGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateObject> {
        TODO("To be implemented")
    }
    
    fun deleteGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateObject> {
        TODO("To be implemented")
    }
}