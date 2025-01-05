package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class MessageReferenceObject(
    val type: Int? = null,
    @SerialName("message_id") val messageId: Snowflake? = null,
    @SerialName("channel_id") val channelId: Snowflake? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("fail_if_not_exists") val failIfNotExists: Boolean? = null
)