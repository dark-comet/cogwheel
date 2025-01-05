package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class SubscriptionObject(
    val id: Snowflake,
    @SerialName("user_id") val userId: Snowflake,
    @SerialName("sku_ids") val skuIds: List<Snowflake>,
    @SerialName("entitlement_ids") val entitlementIds: List<Snowflake>,
    @SerialName("renewal_sku_ids") val renewalSkuIds: List<Snowflake>,
    @SerialName("current_period_start") val currentPeriodStart: ISO8601Timestamp,
    @SerialName("current_period_end") val currentPeriodEnd: ISO8601Timestamp,
    val status: Int,
    @SerialName("canceled_at") val canceledAt: ISO8601Timestamp?,
    val country: String?
)