package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.ChannelId
import xyz.darkcomet.cogwheel.framework.primitives.asChannelId

data class AutoModerationActionMetadata(
    val channelId: ChannelId?,
    val durationSeconds: Int?,
    val customMessage: String?
) {
    companion object {
        internal fun from(obj: AutoModerationActionMetadataObject): AutoModerationActionMetadata {
            return obj.toModel()
        }
    }
    
    fun toObject(): AutoModerationActionMetadataObject {
        return AutoModerationActionMetadataObject(
            channelId = if (this.channelId != null) MaybeAbsent(channelId.snowflake) else null,
            durationSeconds = if (durationSeconds != null) MaybeAbsent(durationSeconds) else null,
            customMessage = if (customMessage != null) MaybeAbsent(customMessage) else null
        )
    }
}

internal fun AutoModerationActionMetadataObject.toModel(): AutoModerationActionMetadata {
    return AutoModerationActionMetadata(
        channelId = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::channelId)?.asChannelId(),
        durationSeconds = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::durationSeconds),
        customMessage = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::customMessage),
    )
}