package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class InviteCreateEvent
internal constructor(override val data: DataObject) : Event<InviteCreateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("channel_id") val channelId: Snowflake,
        val code: String,
        @SerialName("created_at") val createdAt: ISO8601Timestamp,
        @SerialName("guild_id") val guildId: Snowflake? = null,
        val inviter: UserObject? = null,
        @SerialName("max_age") val maxAge: Int,
        @SerialName("max_uses") val maxUses: Int,
        @SerialName("target_type") val targetType: Int? = null,
        @SerialName("target_user") val targetUser: UserObject? = null,
        @SerialName("target_application") val targetApplication: ApplicationObject? = null,
        val temporary: Boolean,
        val uses: Int,
    )

}