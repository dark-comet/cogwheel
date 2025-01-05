package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class StickerPackObject(
    val id: Snowflake,
    val stickers: List<StickerObject>,
    val name: String,
    @SerialName("sku_id") val skuId: Snowflake,
    @SerialName("cover_sticker_id") val coverStickerId: Snowflake? = null,
    val description: String,
    @SerialName("banner_asset_id") val bannerAssetId: Snowflake? = null
)