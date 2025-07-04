package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.framework.primitives.UserId

data class Team(
    val icon: DiscordImage?,
    val members: Array<TeamMember>,
    val name: String,
    val ownerUserId: UserId
)