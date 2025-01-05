package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class StickerObject(
    val id: Snowflake,
    @SerialName("pack_id") val packId: Snowflake? = null,
    val name: String,
    val description: String,
    val tags: String,
    val type: Int,
    @SerialName("format_type") val formatType: Int,
    val available: Boolean? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val user: UserObject? = null,
    @SerialName("sort_value") val sortValue: Int? = null,
)