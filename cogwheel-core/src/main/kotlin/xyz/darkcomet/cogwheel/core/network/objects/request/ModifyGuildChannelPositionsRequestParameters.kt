package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildChannelPositionsRequestParameters(
    val id: Snowflake,
    val position: Int? = null,
    @SerialName("lock_permissions") val lockPermissions: Boolean? = null,
    @SerialName("parent_id") val parentId: Snowflake? = null
)