package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutoModerationRuleTriggerMetadataObject(
    @SerialName("keyword_filter") val keywordFilter: List<String>,
    @SerialName("regex_patterns") val regexPatterns: List<String>,
    val presets: List<Int>,
    @SerialName("allow_list") val allowList: List<String>,
    @SerialName("mention_total_limit") val mentionTotalLimit: Int,
    @SerialName("mention_raid_protection_enabled") val mentionRaidProtectionEnabled: Boolean
)