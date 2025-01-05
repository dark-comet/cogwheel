package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class SoundboardSoundObject(
    val name: String,
    @SerialName("sound_id") val soundId: Snowflake,
    val volume: Double,
    @SerialName("emoji_id") val emojiId: Snowflake?,
    @SerialName("emoji_name") val emojiName: String?,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val available: Boolean,
    val user: UserObject? = null
)