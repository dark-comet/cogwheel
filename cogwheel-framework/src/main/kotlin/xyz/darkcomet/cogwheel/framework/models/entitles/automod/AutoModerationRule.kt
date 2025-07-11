package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject
import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationEventType
import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationRuleId
import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationTriggerType
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.RoleId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

data class AutoModerationRule(
    val id: AutoModerationRuleId,
    val guildId: GuildId,
    val name: String,
    val creatorId: UserId,
    val eventType: AutoModerationEventType,
    val triggerType: AutoModerationTriggerType,
    val triggerMetadata: AutoModerationRuleTriggerMetadata,
    val actions: List<AutoModerationAction>,
    val enabled: Boolean,
    val exemptRoles: List<RoleId>,
    val exemptChannels: List<ChannelId>,
) {
    companion object {
        internal fun from(obj: GuildAutoModerationRuleObject): AutoModerationRule {
            return obj.toModel()
        }
    }
}

internal fun GuildAutoModerationRuleObject.toModel(): AutoModerationRule {
    TODO()
}