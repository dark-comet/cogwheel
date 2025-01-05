package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildWelcomeScreenChannelObject(
    @SerialName("channel_id") val channelId: Snowflake,
    val description: String,
    @SerialName("emoji_id") val emojiId: Snowflake?,
    @SerialName("emoji_name") val emojiName: String?
)