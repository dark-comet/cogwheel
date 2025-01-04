package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationRuleTriggerMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class CreateGuildAutoModerationRuleRequestParameters(
    val name: String,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_type") val triggerType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: AutoModerationRuleTriggerMetadataObject? = null,
    val actions: List<AutoModerationActionObject>,
    val enabled: Boolean? = null,
    @SerialName("exempt_roles") val exemptRoles: List<Snowflake>? = null,
    @SerialName("exempt_channels") val exemptChannels: List<Snowflake>? = null,
)