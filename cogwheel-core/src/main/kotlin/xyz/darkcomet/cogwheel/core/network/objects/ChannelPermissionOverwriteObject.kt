package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ChannelPermissionOverwriteObject(
    val id: Snowflake,
    val type: Int,
    val allow: String,
    val deny: String
)