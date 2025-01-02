package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.models.Snowflake

@Serializable
data class AvatarDecorationDataEntity(
    val asset: String,
    @SerialName("sku_id") val skuId: Snowflake
)