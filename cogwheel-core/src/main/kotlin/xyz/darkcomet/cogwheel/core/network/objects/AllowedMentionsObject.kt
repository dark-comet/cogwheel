package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AllowedMentionsObject(
    val parse: List<String>,
    val roles: List<Snowflake>,
    val users: List<Snowflake>,
    @SerialName("replied_user") val repliedUser: Boolean
)