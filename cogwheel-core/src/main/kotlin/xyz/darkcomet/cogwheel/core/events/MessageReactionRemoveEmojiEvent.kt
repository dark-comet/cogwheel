package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageReactionRemoveEmojiEvent
internal constructor(override val data: DataObject) : Event<MessageReactionRemoveEmojiEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        @SerialName("message_id") val messageId: Snowflake,
        val emoji: EmojiObject
    )

}