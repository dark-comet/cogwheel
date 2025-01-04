package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyThreadChannelRequestParameters(
    val name: String? = null,
    val archived: Boolean? = null,
    @SerialName("auto_archive_duration") val autoArchiveDuration: Int? = null,
    val locked: Boolean? = null,
    val invitable: Boolean? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val flags: Int? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null
)