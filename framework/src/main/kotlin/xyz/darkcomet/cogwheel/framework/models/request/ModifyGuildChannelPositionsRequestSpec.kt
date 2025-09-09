package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildChannelPositionRequestParameters
import xyz.darkcomet.cogwheel.framework.primitives.GuildId

class ModifyGuildChannelPositionsRequestSpec(
    internal val guildId: GuildId
) {
    internal var modifiedPositions: List<ModifyGuildChannelPositionRequestParameters> = mutableListOf()
}