package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ReactionCountDetailsObject(
    val burst: Int,
    val normal: Int
)