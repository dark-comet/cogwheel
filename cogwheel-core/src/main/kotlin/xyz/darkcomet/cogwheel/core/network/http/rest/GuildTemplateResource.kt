package xyz.darkcomet.cogwheel.core.network.http.rest

import xyz.darkcomet.cogwheel.core.network.objects.GuildEntity
import xyz.darkcomet.cogwheel.core.network.objects.GuildTemplateEntity
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildTemplateResource 
internal constructor(private val httpClient: CwHttpClient) {
    
    fun getGuildTemplate(templateCode: String): CwHttpResponse<GuildTemplateEntity> {
        TODO("To be implemented")
    }
    
    fun createGuildFromGuildTemplate(templateCode: String): CwHttpResponse<GuildEntity> {
        TODO("To be implemented")
    }
    
    fun getGuildTemplates(guildId: Snowflake): CwHttpResponse<List<GuildTemplateEntity>> {
        TODO("To be implemented")
    }
    
    fun createGuildTemplate(guildId: Snowflake): CwHttpResponse<GuildTemplateEntity> {
        TODO("To be implemented")
    }
    
    fun syncGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateEntity> {
        TODO("To be implemented")
    }
    
    fun modifyGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateEntity> {
        TODO("To be implemented")
    }
    
    fun deleteGuildTemplate(guildId: Snowflake, templateCode: String): CwHttpResponse<GuildTemplateEntity> {
        TODO("To be implemented")
    }
}