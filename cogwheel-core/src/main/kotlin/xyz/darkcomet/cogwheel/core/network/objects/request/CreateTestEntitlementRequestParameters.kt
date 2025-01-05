package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTestEntitlementRequestParameters(
    @SerialName("sku_id") val skuId: String,
    @SerialName("owner_id") val ownerId: String,
    @SerialName("owner_type") val ownerType: Int
)