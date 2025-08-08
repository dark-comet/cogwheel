@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.channel

import xyz.darkcomet.cogwheel.core.network.objects.ThreadMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent

class ThreadMetadata(
    val archived: Boolean,
    val autoArchiveDuration: Int,
    val archiveTimestamp: ISO8601Timestamp,
    val locked: Boolean,
    val invitable: Boolean?,
    val createTimestamp: ISO8601Timestamp?
) {
    companion object {
        internal fun from(obj: ThreadMetadataObject): ThreadMetadata {
            return obj.toModel()
        }
    }
}

internal fun ThreadMetadataObject.toModel(): ThreadMetadata {
    return ThreadMetadata(
        archived = requireNonNull(this, ThreadMetadataObject::archived),
        autoArchiveDuration = requireNonNull(this, ThreadMetadataObject::autoArchiveDuration),
        archiveTimestamp = requireNonNull(this, ThreadMetadataObject::archiveTimestamp),
        locked = requireNonNull(this, ThreadMetadataObject::locked),
        invitable = requireNonNullIfPresent(this, ThreadMetadataObject::invitable),
        createTimestamp = this.createTimestamp?.value,
    )
}