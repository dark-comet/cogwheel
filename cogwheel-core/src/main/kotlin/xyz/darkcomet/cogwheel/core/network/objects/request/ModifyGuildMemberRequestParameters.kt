package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildMemberRequestParameters(
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: ISO8601Timestamp? = null,
    val flags: Int? = null
)