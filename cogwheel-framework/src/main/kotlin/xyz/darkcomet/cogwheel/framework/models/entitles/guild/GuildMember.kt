package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject

class GuildMember {
}

internal fun GuildMemberObject.toModel(): GuildMember {
    return GuildMember()
}