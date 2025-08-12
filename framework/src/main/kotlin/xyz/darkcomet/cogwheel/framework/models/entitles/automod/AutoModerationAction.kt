package xyz.darkcomet.cogwheel.framework.models.entitles.automod

import xyz.darkcomet.cogwheel.core.network.objects.AutoModerationActionObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
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
        return AutoModerationActionObject(
            type = MaybeAbsent(this.type.key),
            metadata = MaybeAbsent(this.metadata?.toObject())
        )
    }
}

internal fun AutoModerationActionObject.toModel(): AutoModerationAction {
    return AutoModerationAction(
        type = requireNonNull(this, AutoModerationActionObject::type).let { AutoModerationActionType.fromOrAdd(it) },
        metadata = requireNonNullIfPresent(this, AutoModerationActionObject::metadata)?.let { AutoModerationActionMetadata.from(it) }
    )
}