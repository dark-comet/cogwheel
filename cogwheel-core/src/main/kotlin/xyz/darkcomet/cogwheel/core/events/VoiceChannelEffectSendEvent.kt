package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class VoiceChannelEffectSendEvent
internal constructor(override val data: DataObject) : Event<VoiceChannelEffectSendEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        @SerialName("user_id") val userId: Snowflake,
        val emoji: EmojiObject? = null,
        @SerialName("animation_type") val animationType: Int? = null,
        @SerialName("animation_id") val animationId: Int? = null,
        @SerialName("sound_id") val soundId: Snowflake,
        @SerialName("sound_volume") val soundVolume: Double
    )

}