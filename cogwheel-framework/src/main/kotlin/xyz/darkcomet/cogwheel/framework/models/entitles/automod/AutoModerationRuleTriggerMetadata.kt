package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationRuleTriggerMetadataObject
import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationKeywordPresetType

data class AutoModerationRuleTriggerMetadata(
    val keywordFilter: List<String>,
    val regexPatterns: List<String>,
    val presets: List<AutoModerationKeywordPresetType>,
    val allowList: List<String>,
    val mentionTotalLimit: Int,
    val mentionRaidProtectionEnabled: Boolean
) {
    companion object {
        internal fun from(obj: AutoModerationRuleTriggerMetadataObject): AutoModerationRuleTriggerMetadata {
            return obj.toModel()
        }
    }
}

internal fun AutoModerationRuleTriggerMetadataObject.toModel(): AutoModerationRuleTriggerMetadata {
    TODO()
}