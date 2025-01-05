package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class RoleSubscriptionDataObject(
    @SerialName("role_subscription_listing_id") val roleSubscriptionListingId: Snowflake,
    @SerialName("tier_name") val tierName: String,
    @SerialName("total_months_subscribed") val totalMonthsSubscribed: Int,
    @SerialName("is_renewal") val isRenewal: Boolean
)