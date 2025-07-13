package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.framework.primitives.AutoModerationActionType

data class AutoModerationAction(
    val type: AutoModerationActionType,
    val metadata: AutoModerationActionMetadata?
) {
    companion object {
        internal fun from(obj: AutoModerationActionObject): AutoModerationAction {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): AutoModerationActionObject {
        TODO()
    }
}

internal fun AutoModerationActionObject.toModel(): AutoModerationAction {
    TODO()
}