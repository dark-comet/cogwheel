package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildOnboardingPromptOptionObject(
    val id: Snowflake,
    @SerialName("channel_ids") val channelIds: List<Snowflake>,
    @SerialName("role_ids") val roleIds: List<Snowflake>,
    val emoji: EmojiObject? = null,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null,
    @SerialName("emoji_animated") val emojiAnimated: Boolean? = null,
    val title: String,
    val description: String? = null
)