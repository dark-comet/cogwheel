package xyz.darkcomet.cogwheel.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.models.Snowflake

@Serializable
data class ApplicationInstanceEntity(
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("instance_id") val instanceId: String,
    @SerialName("launch_id") val launchId: Snowflake,
    val location: ActivityLocationEntity,
    val users: List<Snowflake>,
)