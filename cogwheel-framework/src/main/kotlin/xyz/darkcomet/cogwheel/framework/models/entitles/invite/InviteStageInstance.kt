@file:Suppress("unused", "DEPRECATION")

package xyz.darkcomet.cogwheel.framework.models.entitles.invite

import xyz.darkcomet.cogwheel.core.network.objects.InviteStageInstanceObject
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.GuildMember
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toModel
import xyz.darkcomet.cogwheel.framework.models.requireNonNull

@Deprecated("to be removed by Discord API")
class InviteStageInstance(
    val members: List<GuildMember>,
    val participantCount: Int,
    val speakerCount: Int,
    val topic: String
) {
    companion object {
        internal fun from(obj: InviteStageInstanceObject): InviteStageInstance {
            return obj.toModel()
        }
    }
}

internal fun InviteStageInstanceObject.toModel(): InviteStageInstance {
    return InviteStageInstance(
        members = requireNonNull(this, InviteStageInstanceObject::members).map { it.toModel() },
        participantCount = requireNonNull(this, InviteStageInstanceObject::participationCount),
        speakerCount = requireNonNull(this, InviteStageInstanceObject::speakerCount),
        topic = requireNonNull(this, InviteStageInstanceObject::topic)
    )
}