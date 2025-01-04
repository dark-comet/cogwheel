package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable

@Serializable
data class GuildScheduledEventMetadataObject(
    val location: String? = null
)