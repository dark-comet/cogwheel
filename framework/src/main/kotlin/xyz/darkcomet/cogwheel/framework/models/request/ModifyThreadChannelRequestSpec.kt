package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyThreadChannelRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.BitField
import xyz.darkcomet.cogwheel.framework.primitives.ChannelFlag
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.ForumTagId

class ModifyThreadChannelRequestSpec(
    internal val channelId: ChannelId
) {
    private var name: MaybeAbsent<String>? = null
    private var archived: MaybeAbsent<Boolean>? = null
    private var autoArchiveDuration: MaybeAbsent<Int>? = null
    private var locked: MaybeAbsent<Boolean>? = null
    private var invitable: MaybeAbsent<Boolean>? = null
    private var rateLimitPerUser: MaybeAbsent<Int?>? = null
    private var flags: MaybeAbsent<Int>? = null
    private var appliedTags: MaybeAbsent<List<Snowflake>>? = null
    
    internal var auditLogReason: String? = null
    
    fun name(name: String): ModifyThreadChannelRequestSpec
        = apply { this.name = MaybeAbsent(name) }

    fun archived(archived: Boolean): ModifyThreadChannelRequestSpec
        = apply { this.archived = MaybeAbsent(archived) }

    fun autoArchiveDuration(minutes: Int): ModifyThreadChannelRequestSpec
        = apply { this.autoArchiveDuration = MaybeAbsent(minutes) }
    
    fun locked(locked: Boolean): ModifyThreadChannelRequestSpec
        = apply { this.locked = MaybeAbsent(locked) }
    
    fun invitable(invitable: Boolean): ModifyThreadChannelRequestSpec
        = apply { this.invitable = MaybeAbsent(invitable) }
    
    fun rateLimitPerUser(minutes: Int?): ModifyThreadChannelRequestSpec
        = apply { this.rateLimitPerUser = MaybeAbsent(minutes) }

    fun flags(flags: BitField<ChannelFlag>): ModifyThreadChannelRequestSpec
        = apply { this.flags = MaybeAbsent(flags.toInt()) }

    fun flags(vararg flags: ChannelFlag): ModifyThreadChannelRequestSpec
        = apply { this.flags = MaybeAbsent(BitField.from(*flags).toInt()) }

    fun appliedTags(vararg tagIds: ForumTagId): ModifyThreadChannelRequestSpec
        = apply { this.appliedTags = MaybeAbsent(tagIds.map { it.snowflake }) }

    fun appliedTags(tagIds: Collection<ForumTagId>): ModifyThreadChannelRequestSpec
        = apply { this.appliedTags = MaybeAbsent(tagIds.map { it.snowflake }) }
    
    fun auditLogReason(reason: String): ModifyThreadChannelRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): ModifyThreadChannelRequestParameters {
        return ModifyThreadChannelRequestParameters(
            name = this.name,
            archived = this.archived,
            autoArchiveDuration = this.autoArchiveDuration,
            locked = this.locked,
            invitable = this.invitable,
            rateLimitPerUser = this.rateLimitPerUser,
            flags = this.flags,
            appliedTags = this.appliedTags
        )
    }
}