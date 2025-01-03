package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ApplicationActivityInstanceObject(
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("instance_id") val instanceId: String,
    @SerialName("launch_id") val launchId: Snowflake,
    val location: ActivityLocationObject,
    val users: List<Snowflake>,
)