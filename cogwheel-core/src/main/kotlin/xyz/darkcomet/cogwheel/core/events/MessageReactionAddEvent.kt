package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.EmojiObject
import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class MessageReactionAddEvent
internal constructor(override val data: DataObject) : Event<MessageReactionAddEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("user_id") val userId: Snowflake,
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("message_id") val messageId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        val member: GuildMemberObject? = null,
        val emoji: EmojiObject,
        @SerialName("message_author_id") val messageAuthorId: Snowflake? = null,
        val burst: Boolean,
        @SerialName("burst_colors") val burstColors: List<String>,
        val type: Int
    )

}