package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class AuditLogEntryObject(
    @SerialName("target_id") val targetId: String?,
    val changes: List<AuditLogChangeObject>? = null,
    val userId: Snowflake?,
    val id: Snowflake,
    @SerialName("action_type") val actionType: Int,
    val options: AuditLogOptionalAuditEntryInfoObject? = null,
    val reason: String? = null
)