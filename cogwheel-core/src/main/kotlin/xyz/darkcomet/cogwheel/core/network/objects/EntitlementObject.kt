package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class EntitlementObject(
    val id: Snowflake,
    @SerialName("sku_id") val skuId: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("user_id") val userId: Snowflake? = null,
    val type: Int,
    val deleted: Boolean,
    @SerialName("starts_at") val startsAt: ISO8601Timestamp? = null,
    @SerialName("ends_at") val endsAt: ISO8601Timestamp? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val consumed: Boolean? = null
)