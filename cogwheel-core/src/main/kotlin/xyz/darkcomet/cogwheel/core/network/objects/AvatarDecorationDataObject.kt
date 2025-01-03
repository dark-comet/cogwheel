package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AvatarDecorationDataObject(
    val asset: String,
    @SerialName("sku_id") val skuId: Snowflake
)