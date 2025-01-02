package xyz.darkcomet.cogwheel.core.network.entities.response

import kotlinx.serialization.Serializable

@Serializable
data class GuildBeginPruneResponseEntity(
    val pruned: Int
)