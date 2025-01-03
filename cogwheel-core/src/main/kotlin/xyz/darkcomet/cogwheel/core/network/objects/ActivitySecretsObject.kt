package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class ActivitySecretsObject(
    val join: String? = null,
    val spectate: String? = null,
    val match: String? = null
)