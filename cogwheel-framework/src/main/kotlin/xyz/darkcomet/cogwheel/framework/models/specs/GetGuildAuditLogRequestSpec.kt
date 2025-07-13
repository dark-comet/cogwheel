package xyz.darkcomet.cogwheel.framework.models.specs

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEventType
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GetGuildAuditLogRequestSpec(internal val guildId: GuildId) {
    
    internal var userId: Snowflake? = null
    internal var actionType: Int? = null
    internal var before: Snowflake? = null
    internal var after: Snowflake? = null
    
    fun userId(id: UserId?) {
        this.userId = id?.snowflake
    }
    
    fun actionType(type: AuditLogEventType?) {
        this.actionType = type?.key
    }
    
    fun before(id: Snowflake?) {
        this.before = id
    }
    
    fun after(id: Snowflake?) {
        this.after = id
    }
    
}