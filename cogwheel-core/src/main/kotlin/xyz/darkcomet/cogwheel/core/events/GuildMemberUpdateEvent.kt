package xyz.darkcomet.cogwheel.core.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.AvatarDecorationDataObject
import xyz.darkcomet.cogwheel.core.network.objects.UserObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

class GuildMemberUpdateEvent
internal constructor(override val data: DataObject) : Event<GuildMemberUpdateEvent.DataObject> {

    @Serializable
    data class DataObject(
        @SerialName("guild_id") val guildId: Snowflake,
        val roles: List<Snowflake>,
        val user: UserObject,
        val nick: String? = null,
        val avatar: String? = null,
        val banner: String? = null,
        @SerialName("joined_at") val joinedAt: ISO8601Timestamp? = null,
        @SerialName("premium_since") val premiumSince: ISO8601Timestamp? = null,
        val deaf: Boolean? = null,
        val mute: Boolean? = null,
        val pending: Boolean? = null,
        @SerialName("communication_disabled_until") val communicationDisabledUntil: ISO8601Timestamp? = null,
        val flags: Int? = null,
        @SerialName("avatar_decoration_data") val avatarDecorationData: AvatarDecorationDataObject? = null
    )

}