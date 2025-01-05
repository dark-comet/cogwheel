package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class VoiceStateObject(
    @SerialName("guild_id") val guildId: Snowflake?,
    @SerialName("channel_id") val channelId: Snowflake,
    @SerialName("user_id") val userId: Snowflake,
    val member: GuildMemberObject? = null,
    @SerialName("session_id") val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    @SerialName("self_deaf") val selfDeaf: Boolean,
    @SerialName("self_mute") val selfMute: Boolean,
    @SerialName("self_stream") val selfStream: Boolean? = null,
    @SerialName("self_video") val selfVideo: Boolean,
    val suppress: Boolean,
    @SerialName("request_to_speak_timestamp") val requestToSpeakTimestamp: ISO8601Timestamp?
)