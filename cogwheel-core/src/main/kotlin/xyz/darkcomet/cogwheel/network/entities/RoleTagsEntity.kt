package xyz.darkcomet.cogwheel.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.Snowflake

@Serializable
data class RoleTagsEntity(
    val botId: Snowflake? = null,
    @SerialName("integration_id") val integrationId: Snowflake? = null,
    @SerialName("premium_subscriber") val premiumSubscriber: Boolean? = null,
    @SerialName("subscription_listing_id") val subscriptionListingId: Snowflake? = null,
    @SerialName("available_for_purchase") val availableForPurchase: Boolean? = null,
    @SerialName("guild_connections") val guildConnections: Boolean? = null
)