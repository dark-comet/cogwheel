package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildWidgetObject(
    val id: Snowflake,
    val name: String,
    @SerialName("instant_invite") val instantInvite: String,
    val channels: List<ChannelObject>,
    val members: List<GuildMemberObject>,
    @SerialName("presence_count") val presenceCount: Int
)