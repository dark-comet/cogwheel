package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class IntegrationApplicationObject(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val description: String,
    val bot: UserObject? = null
)