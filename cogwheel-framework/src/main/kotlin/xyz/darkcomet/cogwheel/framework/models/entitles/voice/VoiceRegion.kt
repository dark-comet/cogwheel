@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.voice

import xyz.darkcomet.cogwheel.core.network.objects.VoiceRegionObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull

class VoiceRegion(
    val id: String,
    val name: String,
    val optimal: Boolean,
    val deprecated: Boolean,
    val custom: Boolean
) {
    companion object {
        internal fun from(obj: VoiceRegionObject): VoiceRegion {
            return obj.toModel()
        }
    }
}

internal fun VoiceRegionObject.toModel(): VoiceRegion {
    return VoiceRegion(
        id = requireNonNull(this, VoiceRegionObject::id),
        name = requireNonNull(this, VoiceRegionObject::name),
        optimal = requireNonNull(this, VoiceRegionObject::optimal),
        deprecated = requireNonNull(this, VoiceRegionObject::deprecated),
        custom = requireNonNull(this, VoiceRegionObject::custom)
    )
}