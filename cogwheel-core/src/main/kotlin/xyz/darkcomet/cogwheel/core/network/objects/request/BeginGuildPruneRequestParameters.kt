package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class BeginGuildPruneRequestParameters(
    val days: Int = 7,
    val computePruneCount: Boolean = true,
    val includeRoles: MutableList<Snowflake> = mutableListOf(),
    val reason: String? = null
)