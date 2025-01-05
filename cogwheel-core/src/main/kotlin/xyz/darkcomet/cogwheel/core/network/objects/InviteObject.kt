package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

@Serializable
data class InviteObject(
    val type: Int? = null,
    val code: String,
    val uses: Int? = null,
    val guild: GuildObject? = null,
    val channel: ChannelObject? = null,
    val inviter: UserObject? = null,
    @SerialName("target_type") val targetType: Int? = null,
    @SerialName("target_user") val targetUser: UserObject? = null,
    @SerialName("target_application") val targetApplication: ApplicationObject? = null,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int? = null,
    @SerialName("approximate_member_count") val approximateMemberCount: Int? = null,
    @SerialName("expires_at") val expiresAt: ISO8601Timestamp? = null,
    @SerialName("stage_instance") val stageInstance: InviteStageInstanceObject? = null,
    @SerialName("guild_scheduled_event") val guildScheduledEvent: GuildScheduledEventObject? = null,
)