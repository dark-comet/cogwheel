package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject

class GuildAutoModerationRule {
    companion object {
        internal fun from(obj: GuildAutoModerationRuleObject): GuildAutoModerationRule {
            return obj.toModel()
        }
    }
}

internal fun GuildAutoModerationRuleObject.toModel(): GuildAutoModerationRule {
    TODO()
}