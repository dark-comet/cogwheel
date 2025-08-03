@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEntryId
import xyz.darkcomet.cogwheel.framework.primitives.AuditLogEventType
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class GetGuildAuditLogRequestSpec(internal val guildId: GuildId) {
    
    internal var userId: Snowflake? = null
    internal var actionType: Int? = null
    internal var before: Snowflake? = null
    internal var after: Snowflake? = null
    
    fun userId(id: UserId?) : GetGuildAuditLogRequestSpec
        = apply { this.userId = id?.snowflake }
    
    fun actionType(type: AuditLogEventType?) : GetGuildAuditLogRequestSpec
        = apply { this.actionType = type?.key }
    
    fun before(id: AuditLogEntryId?) : GetGuildAuditLogRequestSpec
        = apply { this.before = id?.snowflake }
    
    fun after(id: AuditLogEntryId?) : GetGuildAuditLogRequestSpec
        = apply { this.after = id?.snowflake }
    
}