@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.guild

import xyz.darkcomet.cogwheel.core.network.objects.GuildScheduledEventEntityMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent

class GuildScheduledEventEntityMetadata(
    val location: String?
) {
    companion object {
        internal fun from(obj: GuildScheduledEventEntityMetadataObject): GuildScheduledEventEntityMetadata {
            return obj.toModel()
        }
    }
    
    internal fun toObject(): GuildScheduledEventEntityMetadataObject {
        return GuildScheduledEventEntityMetadataObject(
            location = if (location != null) MaybeAbsent(location) else null
        )
    }
    
    class BuilderSpec {
        private var location: String? = null
        
        fun location(location: String?): BuilderSpec
            = apply { this.location = location }
        
        fun build(): GuildScheduledEventEntityMetadata {
            return GuildScheduledEventEntityMetadata(
                location = this.location
            )
        }
    }
}

internal fun GuildScheduledEventEntityMetadataObject.toModel(): GuildScheduledEventEntityMetadata {
    return GuildScheduledEventEntityMetadata(
        location = requireNonNullIfPresent(this, GuildScheduledEventEntityMetadataObject::location)
    )
}