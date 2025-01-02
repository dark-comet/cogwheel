package xyz.darkcomet.cogwheel.core.network.entities.response

import kotlinx.serialization.Serializable

@Serializable
data class GuildGetPruneCountResponseEntity(
    val pruned: Int
)