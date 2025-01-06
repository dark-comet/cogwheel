package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.GuildMemberObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class TypingStartEvent
internal constructor(override val data: DataObject) : Event<TypingStartEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        @SerialName("user_id") val userId: Snowflake,
        val timestamp: Long,
        val member: GuildMemberObject? = null,
    )

}