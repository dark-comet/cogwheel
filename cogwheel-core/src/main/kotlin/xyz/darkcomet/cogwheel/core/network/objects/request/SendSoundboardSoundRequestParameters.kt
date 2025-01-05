package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class SendSoundboardSoundRequestParameters(
    @SerialName("sound_id") val soundId: Snowflake,
    @SerialName("source_guild_id") val sourceGuildId: Snowflake,
)