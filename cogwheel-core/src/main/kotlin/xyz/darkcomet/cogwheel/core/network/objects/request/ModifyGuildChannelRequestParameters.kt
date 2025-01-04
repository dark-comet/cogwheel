package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.ChannelDefaultReactionObject
import xyz.darkcomet.cogwheel.core.network.objects.ChannelPermissionOverwriteObject
import xyz.darkcomet.cogwheel.core.network.objects.ForumTagObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildChannelRequestParameters(
    val name: String? = null,
    val type: Int? = null,
    val position: Int? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("rate_limit_per_user") val rateLimitPerUser: Int? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit") val userLimit: Int? = null,
    @SerialName("permission_overwrites") val permissionOverwrites: List<ChannelPermissionOverwriteObject>? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null,
    @SerialName("rtc_region") val rtcRegion: String? = null,
    @SerialName("video_quality_mode") val videoQualityMode: Int? = null,
    @SerialName("default_auto_archive_duration") val defaultAutoArchiveDuration: Int? = null,
    val flags: Int? = null,
    @SerialName("available_tags") val availableTags: List<ForumTagObject>? = null,
    @SerialName("default_reaction_emoji") val defaultReactionEmoji: ChannelDefaultReactionObject? = null,
    @SerialName("default_thread_rate_limit_per_user") val defaultThreadRateLimitPerUser: Int? = null,
    @SerialName("default_sort_order") val defaultSortOrder: Int? = null,
    @SerialName("default_forum_layout") val defaultForumLayout: Int? = null,
)