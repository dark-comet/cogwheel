package xyz.darkcomet.cogwheel.core.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.models.Snowflake

@Serializable
data class GuildPreviewEntity(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash") val discoverySplash: String?,
    val emojis: List<EmojiEntity>,
    val features: List<String>,
    @SerialName("approximate_member_count") val approximateMemberCount: Int,
    @SerialName("approximate_presence_count") val approximatePresenceCount: Int,
    val description: String? = null,
    val stickers: List<StickerEntity>
)