package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class InteractionDataObject(
    val id: Snowflake,
    val name: String,
    val type: Int,
    val resolved: ResolvedDataObject? = null,
    val options: List<ApplicationCommandInteractionDataOptionObject>? = null,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    @SerialName("target_id") val targetId: Snowflake? = null
)