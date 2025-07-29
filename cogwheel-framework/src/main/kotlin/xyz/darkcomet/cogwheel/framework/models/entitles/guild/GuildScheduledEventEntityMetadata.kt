@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent

class GuildScheduledEventEntityMetadata(
    val location: String?
) {
    companion object {
        internal fun from(obj: GuildScheduledEventEntityMetadataObject): GuildScheduledEventEntityMetadata {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): GuildScheduledEventEntityMetadataObject {
//        return GuildScheduledEventEntityMetadataObject(
//            location = location // TODO: Discern between MaybeAbsent<T>?
//        )
        TODO()
    }
}

internal fun GuildScheduledEventEntityMetadataObject.toModel(): GuildScheduledEventEntityMetadata {
    return GuildScheduledEventEntityMetadata(
        location = requireNonNullIfPresent(this, GuildScheduledEventEntityMetadataObject::location)
    )
}