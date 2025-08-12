@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ChannelDefaultReactionObject
import xyz.darkcomet.cogwheel.core.network.objects.ChannelPermissionOverwriteObject
import xyz.darkcomet.cogwheel.core.network.objects.ForumTagObject
import xyz.darkcomet.cogwheel.core.network.objects.ModifyGuildChannelRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ChannelDefaultReaction
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.ForumTag
import xyz.darkcomet.cogwheel.framework.models.entitles.channel.PartialChannelPermissionOverwrite
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelFlag
import xyz.darkcomet.cogwheel.framework.primitives.ChannelForumLayoutType
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.ChannelSortOrderType
import xyz.darkcomet.cogwheel.framework.primitives.ChannelType
import xyz.darkcomet.cogwheel.framework.primitives.ChannelVideoQualityMode

class ModifyGuildChannelRequestSpec(
    internal val channelId: ChannelId
) {
    private var name: MaybeAbsent<String>? = null
    private var type: MaybeAbsent<Int>? = null
    private var position: MaybeAbsent<Int?>? = null
    private var topic: MaybeAbsent<String?>? = null
    private var nsfw: MaybeAbsent<Boolean?>? = null
    private var rateLimitPerUser: MaybeAbsent<Int?>? = null
    private var bitrate: MaybeAbsent<Int?>? = null
    private var userLimit: MaybeAbsent<Int?>? = null
    private var permissionOverwrites: MaybeAbsent<List<ChannelPermissionOverwriteObject>?>? = null
    private var parentId: MaybeAbsent<Snowflake?>? = null
    private var rtcRegion: MaybeAbsent<String?>? = null
    private var videoQualityMode: MaybeAbsent<Int?>? = null
    private var defaultAutoArchiveDuration: MaybeAbsent<Int?>? = null
    private var flags: MaybeAbsent<Int>? = null
    private var availableTags: MaybeAbsent<List<ForumTagObject>>? = null
    private var defaultReactionEmoji: MaybeAbsent<ChannelDefaultReactionObject?>? = null
    private var defaultThreadRateLimitPerUser: MaybeAbsent<Int>? = null
    private var defaultSortOrder: MaybeAbsent<Int?>? = null
    private var defaultForumLayout: MaybeAbsent<Int>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyGuildChannelRequestSpec
        = apply { this.name = MaybeAbsent(name) }
    
    fun type(type: ChannelType?): ModifyGuildChannelRequestSpec
        = apply { this.type = MaybeAbsent(type?.key) }
    
    fun position(position: Int?): ModifyGuildChannelRequestSpec
        = apply { this.position = MaybeAbsent(position) }
    
    fun topic(topic: String?): ModifyGuildChannelRequestSpec
        = apply { this.topic = MaybeAbsent(topic) }
    
    fun nsfw(nsfw: Boolean?): ModifyGuildChannelRequestSpec
        = apply { this.nsfw = MaybeAbsent(nsfw) }
    
    fun rateLimitPerUser(rateLimitPerUser: Int?): ModifyGuildChannelRequestSpec
        = apply { this.rateLimitPerUser = MaybeAbsent(rateLimitPerUser) }
    
    fun bitrate(bitrate: Int?): ModifyGuildChannelRequestSpec
        = apply { this.bitrate = MaybeAbsent(bitrate) }
    
    fun userLimit(numUsers: Int?): ModifyGuildChannelRequestSpec
        = apply { this.userLimit = MaybeAbsent(numUsers) }
    
    fun permissionOverwrites(overwrites: Collection<PartialChannelPermissionOverwrite>?): ModifyGuildChannelRequestSpec
        = apply { this.permissionOverwrites = MaybeAbsent(overwrites?.map { it.toObject() }) }
    
    fun permissionOverwrite(vararg overwrites: PartialChannelPermissionOverwrite): ModifyGuildChannelRequestSpec
        = apply { this.permissionOverwrites = MaybeAbsent(overwrites.map { it.toObject() }) }
    
    fun parentId(id: ChannelId?): ModifyGuildChannelRequestSpec 
        = apply { this.parentId = MaybeAbsent(id?.snowflake) }
    
    fun rtcRegion(rtcRegion: String?): ModifyGuildChannelRequestSpec
        = apply { this.rtcRegion = MaybeAbsent(rtcRegion) }
    
    fun videoQualityMode(mode: ChannelVideoQualityMode?): ModifyGuildChannelRequestSpec
        = apply { this.videoQualityMode = MaybeAbsent(mode?.key) }
    
    fun defaultAutoArchiveDuration(minutes: Int?): ModifyGuildChannelRequestSpec
        = apply { this.defaultAutoArchiveDuration = MaybeAbsent(minutes) }
    
    fun flags(flags: BitField<ChannelFlag>): ModifyGuildChannelRequestSpec
        = apply { this.flags = MaybeAbsent(flags.toInt()) }
    
    fun flags(vararg flags: ChannelFlag): ModifyGuildChannelRequestSpec
        = apply { this.flags = MaybeAbsent(BitField.from(flags).toInt()) }
    
    fun defaultReactionEmoji(emoji: ChannelDefaultReaction?): ModifyGuildChannelRequestSpec
        = apply { this.defaultReactionEmoji = MaybeAbsent(emoji?.toObject()) }
    
    fun availableTags(tags: Collection<ForumTag>?): ModifyGuildChannelRequestSpec
        = apply { this.availableTags = MaybeAbsent(tags?.map{ it.toObject() }) }
    
    fun availableTags(vararg tags: ForumTag): ModifyGuildChannelRequestSpec
        = apply { this.availableTags = MaybeAbsent(tags.map { it.toObject() }) }
    
    fun defaultSortOrder(order: ChannelSortOrderType?): ModifyGuildChannelRequestSpec
        = apply { this.defaultSortOrder = MaybeAbsent(order?.key) }
    
    fun defaultForumLayout(layout: ChannelForumLayoutType?): ModifyGuildChannelRequestSpec
        = apply { this.defaultForumLayout = MaybeAbsent(layout?.key) }
    
    fun defaultThreadRateLimitPerUser(limit: Int?): ModifyGuildChannelRequestSpec
        = apply { this.defaultThreadRateLimitPerUser = MaybeAbsent(limit) }
    
    fun auditLogReason(reason: String?): ModifyGuildChannelRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): ModifyGuildChannelRequestParameters {
        return ModifyGuildChannelRequestParameters(
            name = this.name, 
            type = this.type,
            position = this.position,
            topic = this.topic,
            nsfw = this.nsfw,
            rateLimitPerUser = this.rateLimitPerUser,
            bitrate = this.bitrate,
            userLimit = this.userLimit,
            permissionOverwrites = this.permissionOverwrites,
            defaultAutoArchiveDuration = this.defaultAutoArchiveDuration,
            parentId = this.parentId,
            rtcRegion = this.rtcRegion,
            videoQualityMode = this.videoQualityMode,
            flags = this.flags,
            availableTags = this.availableTags,
            defaultReactionEmoji = this.defaultReactionEmoji,
            defaultThreadRateLimitPerUser = this.defaultThreadRateLimitPerUser,
            defaultSortOrder = this.defaultSortOrder,
            defaultForumLayout = this.defaultForumLayout,
        )
    }
}