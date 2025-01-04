package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateChannelInviteRequestParameters(
    @SerialName("max_age") val maxAge: Int,
    @SerialName("max_uses") val maxUses: Int,
    val temporary: Boolean,
    val unique: Boolean,
    @SerialName("target_type") val targetType: Int,
    @SerialName("target_user_id") val targetUserId: Snowflake,
    @SerialName("target_application_id") val targetApplicationId: Snowflake
)