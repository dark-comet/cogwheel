package xyz.darkcomet.cogwheel.core.events

import xyz.darkcomet.cogwheel.core.network.objects.GuildAutoModerationRuleObject

class AutoModerationRuleUpdateEvent
internal constructor(
    override val data: GuildAutoModerationRuleObject
): Event<GuildAutoModerationRuleObject>