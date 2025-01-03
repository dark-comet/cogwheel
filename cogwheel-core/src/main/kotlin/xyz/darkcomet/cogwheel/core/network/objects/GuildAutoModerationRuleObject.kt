package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class GuildAutoModerationRuleObject(
    val id: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake,
    val name: String,
    @SerialName("creator_id") val creatorId: Snowflake,
    @SerialName("event_type") val eventType: Int,
    @SerialName("trigger_type") val triggerType: Int,
    @SerialName("trigger_metadata") val triggerMetadata: AutoModerationRuleTriggerMetadataObject,
    val actions: List<AutoModerationActionObject>,
    val enabled: Boolean,
    @SerialName("exempt_roles") val exemptRoles: List<Snowflake>,
    @SerialName("exempt_channels") val exemptChannels: List<Snowflake>
)