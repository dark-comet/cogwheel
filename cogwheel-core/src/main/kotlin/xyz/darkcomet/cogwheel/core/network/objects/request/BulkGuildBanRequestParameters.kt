package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class BulkGuildBanRequestParameters(
    @SerialName("user_ids") val userIds: List<Snowflake>,
    @SerialName("delete_message_seconds") val deleteMessageSeconds: Int? = null
)