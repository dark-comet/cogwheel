@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.entitlement

import xyz.darkcomet.cogwheel.core.network.objects.EntitlementObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementType
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.SubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.asEntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.asGuildId
import xyz.darkcomet.cogwheel.framework.primitives.asSkuId
import xyz.darkcomet.cogwheel.framework.primitives.asSubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId

class Entitlement(
    val id: EntitlementId,
    val skuId: SkuId,
    val applicationId: ApplicationId,
    val userId: UserId?,
    val type: EntitlementType,
    val deleted: Boolean,
    val startsAt: ISO8601Timestamp,
    val endsAt: ISO8601Timestamp,
    val guildId: GuildId?,
    val consumed: Boolean?,
    val subscriptionId: SubscriptionId
) {
    companion object {
        internal fun from(obj: EntitlementObject): Entitlement {
            return obj.toModel()
        }
    }
}

internal fun EntitlementObject.toModel(): Entitlement {
    return Entitlement(
        id = requireNonNull(this, EntitlementObject::id).asEntitlementId(),
        skuId = requireNonNull(this, EntitlementObject::skuId).asSkuId(),
        applicationId = requireNonNull(this, EntitlementObject::applicationId).asApplicationId(),
        userId = requireNonNullIfPresent(this, EntitlementObject::userId)?.asUserId(),
        type = requireNonNull(this, EntitlementObject::type).let { EntitlementType.fromOrAdd(it) },
        deleted = requireNonNull(this, EntitlementObject::deleted),
        startsAt = requireNonNull(this, EntitlementObject::startsAt),
        endsAt = requireNonNull(this, EntitlementObject::endsAt),
        guildId = requireNonNullIfPresent(this, EntitlementObject::guildId)?.asGuildId(),
        consumed = requireNonNullIfPresent(this, EntitlementObject::consumed),
        subscriptionId = requireNonNull(this, EntitlementObject::subscriptionId).asSubscriptionId(),
    )
}