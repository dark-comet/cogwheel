package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildEmojiRequestParameters(
    val name: String? = null,
    val roles: List<Snowflake>? = null,
)