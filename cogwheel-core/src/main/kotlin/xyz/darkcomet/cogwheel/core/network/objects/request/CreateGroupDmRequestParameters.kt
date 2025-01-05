package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateGroupDmRequestParameters(
    val accessTokens: List<String>,
    val nicks: Map<Snowflake, String>
)