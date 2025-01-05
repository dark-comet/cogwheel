package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AddGuildMemberRequestParameters(
    @SerialName("access_token") val accessToken: String,
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null
)