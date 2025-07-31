package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject

class GuildMember {
    companion object {
        internal fun from(obj: GuildMemberObject): GuildMember {
            return obj.toModel()
        }
    }
}

internal fun GuildMemberObject.toModel(): GuildMember {
    return GuildMember()
}