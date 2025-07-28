@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.entitlement

import xyz.darkcomet.cogwheel.core.network.objects.EntitlementObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.*

class PartialEntitlement(
    val id: EntitlementId,
    val skuId: SkuId,
    val applicationId: ApplicationId,
    val userId: UserId?,
    val type: EntitlementType,
    val deleted: Boolean,
    val guildId: GuildId?,
    val consumed: Boolean?
) {
    companion object {
        internal fun from(obj: EntitlementObject): PartialEntitlement {
            return obj.toPartialModel()
        }
    }
}

internal fun EntitlementObject.toPartialModel(): PartialEntitlement {
    return PartialEntitlement(
        id = requireNonNull(this, EntitlementObject::id).asEntitlementId(),
        skuId = requireNonNull(this, EntitlementObject::skuId).asSkuId(),
        applicationId = requireNonNull(this, EntitlementObject::applicationId).asApplicationId(),
        userId = requireNonNullIfPresent(this, EntitlementObject::userId)?.asUserId(),
        type = requireNonNull(this, EntitlementObject::type).let { EntitlementType.fromOrAdd(it) },
        deleted = requireNonNull(this, EntitlementObject::deleted),
        guildId = requireNonNullIfPresent(this, EntitlementObject::guildId)?.asGuildId(),
        consumed = requireNonNullIfPresent(this, EntitlementObject::consumed)
    )
}