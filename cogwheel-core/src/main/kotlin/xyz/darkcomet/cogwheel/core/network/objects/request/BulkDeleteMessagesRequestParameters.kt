package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class BulkDeleteMessagesRequestParameters(
    val messages: List<Snowflake>
)