package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class WebhookObject(
    val id: Snowflake,
    val type: Int,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    val user: UserObject? = null,
    val name: String? = null,
    val avatar: String? = null,
    val token: String? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    @SerialName("source_guild") val sourceGuild: GuildObject? = null,
    @SerialName("source_channel") val sourceChannel: ChannelObject? = null,
    val url: String? = null
)