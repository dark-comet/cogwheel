@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateChannelInviteRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.InviteTargetType
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class CreateChannelInviteRequestSpec(
    internal val channelId: ChannelId,
) {
    private var maxAge: MaybeAbsent<Int>? = null
    private var maxUses: MaybeAbsent<Int>? = null
    private var temporary: MaybeAbsent<Boolean>? = null
    private var unique: MaybeAbsent<Boolean>? = null
    private var targetType: MaybeAbsent<Int>? = null
    private var targetUserId: MaybeAbsent<Snowflake>? = null
    private var targetApplicationId: MaybeAbsent<Snowflake>? = null
    
    internal var auditLogReason: String? = null
    
    fun maxAge(numSeconds: Int): CreateChannelInviteRequestSpec
        = apply { this.maxAge = MaybeAbsent(numSeconds) }
    
    fun maxUses(uses: Int): CreateChannelInviteRequestSpec
        = apply { this.maxUses = MaybeAbsent(uses) }
    
    fun temporary(temporary: Boolean): CreateChannelInviteRequestSpec
        = apply { this.temporary = MaybeAbsent(temporary) }
    
    fun unique(unique: Boolean): CreateChannelInviteRequestSpec
        = apply { this.unique = MaybeAbsent(unique) }
    
    fun targetType(type: InviteTargetType): CreateChannelInviteRequestSpec
        = apply { this.targetType = MaybeAbsent(type.key) }
    
    fun targetUserId(userId: UserId): CreateChannelInviteRequestSpec
        = apply { this.targetUserId = MaybeAbsent(userId.snowflake) }
    
    fun targetApplicationId(appId: ApplicationId): CreateChannelInviteRequestSpec
        = apply { this.targetApplicationId = MaybeAbsent(appId.snowflake) }
    
    fun auditLogReason(reason: String?): CreateChannelInviteRequestSpec
        = apply { auditLogReason = reason }
    
    internal fun buildParameters(): CreateChannelInviteRequestParameters {
        return CreateChannelInviteRequestParameters(
            maxAge = this.maxAge,
            maxUses = this.maxUses,
            temporary = this.temporary,
            unique = this.unique,
            targetType = this.targetType,
            targetUserId = this.targetUserId,
            targetApplicationId = this.targetApplicationId
        )
    }
}