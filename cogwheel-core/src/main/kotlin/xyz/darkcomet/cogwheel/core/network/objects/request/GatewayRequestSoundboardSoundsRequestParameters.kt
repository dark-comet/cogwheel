package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GatewayRequestSoundboardSoundsRequestParameters(
    @SerialName("guild_ids") val guildIds: List<Snowflake>
)