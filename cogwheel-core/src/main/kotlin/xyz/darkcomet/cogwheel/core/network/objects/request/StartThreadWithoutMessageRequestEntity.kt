package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StartThreadWithoutMessageRequestEntity(
    val name: String,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int? = null,
    val type: Int? = null,
    val invitable: Boolean,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null
)