package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionMetadataObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
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
}

internal fun AutoModerationActionMetadataObject.toModel(): AutoModerationActionMetadata {
    return AutoModerationActionMetadata(
        channelId = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::channelId)?.asChannelId(),
        durationSeconds = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::durationSeconds),
        customMessage = requireNonNullIfPresent(this, AutoModerationActionMetadataObject::customMessage),
    )
}