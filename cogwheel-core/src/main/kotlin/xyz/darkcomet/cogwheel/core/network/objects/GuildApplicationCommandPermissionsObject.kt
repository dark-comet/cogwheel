package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildApplicationCommandPermissionsObject(
    val id: Snowflake,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val permissions: List<ApplicationCommandPermissionObject>
)