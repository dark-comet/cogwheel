package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ChannelObject(
    val id: Snowflake,
    val type: Int,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val position: Int? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: List<ChannelPermissionOverwriteObject>? = null,
    val name: String? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("last_message_id") val lastMessageId: String? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit") val userLimit: Int? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val recipients: List<UserObject>? = null,
    val icon: String? = null,
    @SerialName("owner_id") val ownerId: Snowflake? = null,
    @SerialName("application_id") val applicationId: Snowflake? = null,
    val managed: Boolean? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null,
    @SerialName("last_pin_timestamp") val lastPinTimestamp: ISO8601Timestamp? = null,
    @SerialName("rtc_region") val rtcRegion: String? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Int? = null,
    @SerialName("message_count") val messageCount: Int? = null,
    @SerialName("member_count") val memberCount: Int? = null,
    @SerialName("thread_metadata") val threadMetadata: ThreadMetadataObject? = null,
    @SerialName("thread_member") val member: ThreadMemberObject? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Int? = null,
    @SerialName("permissions") val permissions: String? = null,
    val flags: Int? = null,
    @SerialName("total_message_sent") val totalMessageSent: Int? = null,
    @SerialName("available_tags") val availableTags: List<ForumTagObject>? = null,
    @SerialName("applied_tags") val appliedTags: List<Snowflake>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: ChannelDefaultReactionObject? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Int? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Int? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Int? = null,
    val message: MessageObject? = null, // from starting a thread in forum / media channel
)