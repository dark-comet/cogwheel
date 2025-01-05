package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildOnboardingObject(
    @SerialName("guild_id") val guildId: Snowflake,
    val prompts: List<GuildOnboardingPromptObject>,
    @SerialName("default_channel_ids") val defaultChannelIds: List<Snowflake>,
    val enabled: Boolean,
    val mode: Int
)