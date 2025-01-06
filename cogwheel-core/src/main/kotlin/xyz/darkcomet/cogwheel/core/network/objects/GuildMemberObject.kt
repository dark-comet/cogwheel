package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Permissions
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildMemberObject(
    val user: UserObject? = null,
    val nick: String? = null,
    val avatar: ImageData? = null,
    val banner: ImageData? = null,
    val roles: List<Snowflake>,
    @SerialName("joined_at") val joinedAt: ISO8601Timestamp,
    @SerialName("premium_since") val premiumSince: ISO8601Timestamp? = null,
    val deaf: Boolean,
    val mute: Boolean,
    val flags: Int,
    val pending: Boolean? = null,
    val permissions: Permissions? = null,
    @SerialName("communication_disabled_until") val communicationDisabledUntil: ISO8601Timestamp? = null,
    @SerialName("avatar_decoration_data") val avatarDecorationData: AvatarDecorationDataObject? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null
)