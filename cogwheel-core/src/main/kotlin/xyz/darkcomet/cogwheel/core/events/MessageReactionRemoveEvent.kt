package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageReactionRemoveEvent
internal constructor(override val data: DataObject) : Event<MessageReactionRemoveEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        val emoji: EmojiObject,
        val burst: Boolean,
        val type: Int
    )

}