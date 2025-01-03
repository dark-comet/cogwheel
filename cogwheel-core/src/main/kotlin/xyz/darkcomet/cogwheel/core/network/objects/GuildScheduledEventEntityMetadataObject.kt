package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class GuildScheduledEventEntityMetadataObject(
    val location: String? = null
)