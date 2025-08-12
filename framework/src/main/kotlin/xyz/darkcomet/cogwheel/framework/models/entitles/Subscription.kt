@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles

import xyz.darkcomet.cogwheel.core.network.objects.SubscriptionObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.utils.require
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.SubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.SubscriptionStatus
import xyz.darkcomet.cogwheel.framework.primitives.UserId
import xyz.darkcomet.cogwheel.framework.primitives.asEntitlementId
import xyz.darkcomet.cogwheel.framework.primitives.asSkuId
import xyz.darkcomet.cogwheel.framework.primitives.asSubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.asUserId

class Subscription(
    val id: SubscriptionId,
    val userId: UserId,
    val skuIds: List<SkuId>,
    val entitlementIds: List<EntitlementId>,
    val renewalSkuIds: List<SkuId>?,
    val currentPeriodStart: ISO8601Timestamp,
    val currentPeriodEnd: ISO8601Timestamp,
    val status: SubscriptionStatus,
    val canceledAt: ISO8601Timestamp?,
    val country: String?
) {
    companion object {
        internal fun from(obj: SubscriptionObject): Subscription {
            return obj.toModel()
        }
    }
}

internal fun SubscriptionObject.toModel(): Subscription {
    return Subscription(
        id = requireNonNull(this, SubscriptionObject::id).asSubscriptionId(),
        userId = requireNonNull(this, SubscriptionObject::userId).asUserId(),
        skuIds = requireNonNull(this, SubscriptionObject::skuIds).map { it.asSkuId() },
        entitlementIds = requireNonNull(this, SubscriptionObject::entitlementIds).map { it.asEntitlementId() },
        renewalSkuIds = require(this, SubscriptionObject::renewalSkuIds)?.map { it.asSkuId() },
        currentPeriodStart = requireNonNull(this, SubscriptionObject::currentPeriodStart),
        currentPeriodEnd = requireNonNull(this, SubscriptionObject::currentPeriodEnd),
        status = requireNonNull(this, SubscriptionObject::status).let { SubscriptionStatus.fromOrAdd(it) },
        canceledAt = require(this, SubscriptionObject::canceledAt),
        country = require(this, SubscriptionObject::country) 
    )
}