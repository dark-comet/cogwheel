@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.SubscriptionId
import xyz.darkcomet.cogwheel.framework.primitives.UserId

class ListSkuSubscriptionsRequestSpec(
    internal val skuId: SkuId
) {
    internal var before: Snowflake? = null
    internal var after: Snowflake? = null
    internal var limit: Int? = null
    internal var userId: Snowflake? = null
    
    fun before(subscriptionId: SubscriptionId): ListSkuSubscriptionsRequestSpec
        = apply { this.before = subscriptionId.snowflake }
    
    fun after(subscriptionId: SubscriptionId): ListSkuSubscriptionsRequestSpec
        = apply { this.after = subscriptionId.snowflake }
    
    fun limit(quantity: Int): ListSkuSubscriptionsRequestSpec
        = apply { this.limit = quantity }
    
    fun userId(id: UserId): ListSkuSubscriptionsRequestSpec
        = apply { this.userId = id.snowflake }
}