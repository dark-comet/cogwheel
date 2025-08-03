package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationRuleTriggerMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
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
        fun builder(): BuilderSpec = BuilderSpec()
        
        internal fun from(obj: AutoModerationRuleTriggerMetadataObject): AutoModerationRuleTriggerMetadata {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): AutoModerationRuleTriggerMetadataObject {
        return AutoModerationRuleTriggerMetadataObject(
            keywordFilter = MaybeAbsent(keywordFilter),
            regexPatterns = MaybeAbsent(regexPatterns),
            presets = MaybeAbsent(presets.map { it.key }),
            allowList = MaybeAbsent(allowList),
            mentionTotalLimit = MaybeAbsent(mentionTotalLimit),
            mentionRaidProtectionEnabled = MaybeAbsent(mentionRaidProtectionEnabled)
        )
    }
    
    class BuilderSpec {
        private var keywordFilter: List<String> = listOf()
        private var regexPatterns: List<String> = listOf()
        private var presets: List<AutoModerationKeywordPresetType> = listOf()
        private var allowList: List<String> = listOf()
        private var mentionTotalLimit: Int = 0
        private var mentionRaidProtectionEnabled: Boolean = false
        
        fun keywordFilter(vararg keywords: String): BuilderSpec 
            = apply { this.keywordFilter = keywords.toList() }
        
        fun regexPatterns(vararg regexPatterns: String): BuilderSpec
            = apply { this.regexPatterns = regexPatterns.toList() }
        
        fun presets(vararg presets: AutoModerationKeywordPresetType): BuilderSpec
            = apply { this.presets = presets.toList() }
        
        fun allowList(vararg allowList: String): BuilderSpec
            = apply { this.allowList = allowList.toList() }
        
        fun mentionTotalLimit(count: Int): BuilderSpec
            = apply { this.mentionTotalLimit = count }
        
        fun mentionRaidProtectionEnabled(enabled: Boolean): BuilderSpec
            = apply { this.mentionRaidProtectionEnabled = enabled }
        
        fun build(): AutoModerationRuleTriggerMetadata {
            return AutoModerationRuleTriggerMetadata(
                keywordFilter = keywordFilter,
                regexPatterns = regexPatterns,
                presets = presets,
                allowList = allowList,
                mentionTotalLimit = mentionTotalLimit,
                mentionRaidProtectionEnabled = mentionRaidProtectionEnabled,
            )
        }
    }
}

internal fun AutoModerationRuleTriggerMetadataObject.toModel(): AutoModerationRuleTriggerMetadata {
    return AutoModerationRuleTriggerMetadata(
        keywordFilter = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::keywordFilter),
        regexPatterns = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::regexPatterns),
        presets = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::presets).map { AutoModerationKeywordPresetType.fromOrAdd(it) },
        allowList = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::allowList),
        mentionTotalLimit = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::mentionTotalLimit),
        mentionRaidProtectionEnabled = requireNonNull(this, AutoModerationRuleTriggerMetadataObject::mentionRaidProtectionEnabled),
    )
}