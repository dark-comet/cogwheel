@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ChannelObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.entitles.user.User
import xyz.darkcomet.cogwheel.framework.primitives.*

class Channel(
    val id: ChannelId,
    val type: ChannelType,
    val guildId: GuildId?,
    val position: Int?,
    val permissionOverwrites: List<ChannelPermissionOverwrite>?,
    val name: String?,
    val topic: String?,
    val nsfw: Boolean?,
    val lastMessageId: MessageId?,
    val bitrate: Int?,
    val userLimit: Int?,
    val rateLimitPerUser: Int?,
    val recipients: List<User>?,
    val icon: DiscordImage?,
    val ownerId: UserId,
    val applicationId: ApplicationId?,
    val managed: Boolean?,
    val parentId: ChannelId?,
    val lastPinTimestamp: ISO8601Timestamp?,
    val rtcRegion: String?,
    val videoQualityMode: ChannelVideoQualityMode?,
    val messageCount: Int?,
    val memberCount: Int?,
    val threadMetadata: ThreadMetadata?,
    val member: ThreadMember?,
    val defaultAutoArchiveDuration: Int?,
    val permissions: BitField<Permission>?,
    val flags: BitField<ChannelFlag>?,
    val totalMessageSent: Int?,
    val availableTags: List<ChannelForumTag>?,
    val appliedTags: List<ChannelForumTagId>?,
    val defaultReactionEmoji: ChannelDefaultReaction?,
    val defaultThreadRateLimitPerUser: Int?,
    val defaultSortOrder: ChannelSortOrderType?,
    val defaultForumLayout: ChannelForumLayoutType?
) {
    companion object {
        internal fun from(obj: ChannelObject): Channel {
            return obj.toModel()
        }
    }
}

internal fun ChannelObject.toModel(): Channel {
    TODO()
}