@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationRuleId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class DeleteGuildAutoModerationRuleRequestSpec(
    internal val guildId: GuildId, 
    internal val ruleId: AutoModerationRuleId
) {
    internal var auditLogReason: String? = null

    fun auditLogReason(reason: String?): DeleteGuildAutoModerationRuleRequestSpec
        = apply { this.auditLogReason = reason }
}