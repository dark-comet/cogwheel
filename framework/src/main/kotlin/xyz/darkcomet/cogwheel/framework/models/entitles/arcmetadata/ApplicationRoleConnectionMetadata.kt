package xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationRoleConnectionMetadataObject
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.utils.requireNonNullIfPresent
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationRoleConnectionMetadataType
import xyz.darkcomet.cogwheel.framework.primitives.LocalizationMap

@Suppress("unused")
data class ApplicationRoleConnectionMetadata(
    val type: ApplicationRoleConnectionMetadataType,
    val key: String,
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val description: String,
    val descriptionLocalizations: LocalizationMap?
) {
    internal fun toObject(): ApplicationRoleConnectionMetadataObject {
        return ApplicationRoleConnectionMetadataObject(
            type = MaybeAbsent(this.type.key),
            key = MaybeAbsent(this.key),
            name = MaybeAbsent(this.name),
            nameLocalizations = this.nameLocalizations?.let { MaybeAbsent(this.nameLocalizations.toObject()) },
            description = MaybeAbsent(this.description),
            descriptionLocalizations = this.descriptionLocalizations?.let { MaybeAbsent(this.descriptionLocalizations.toObject()) }
        )
    }
    
    companion object {
        internal fun from(obj: ApplicationRoleConnectionMetadataObject): ApplicationRoleConnectionMetadata {
            return obj.toModel()
        }
    }
}

internal fun ApplicationRoleConnectionMetadataObject.toModel(): ApplicationRoleConnectionMetadata {
    return ApplicationRoleConnectionMetadata(
        type = requireNonNull(this, ApplicationRoleConnectionMetadataObject::type).let{ ApplicationRoleConnectionMetadataType.fromOrAdd(it) },
        key = requireNonNull(this, ApplicationRoleConnectionMetadataObject::key),
        name = requireNonNull(this, ApplicationRoleConnectionMetadataObject::name),
        nameLocalizations = requireNonNullIfPresent(this, ApplicationRoleConnectionMetadataObject::nameLocalizations)?.let { LocalizationMap.from(it) },
        description = requireNonNull(this, ApplicationRoleConnectionMetadataObject::description),
        descriptionLocalizations = requireNonNullIfPresent(this, ApplicationRoleConnectionMetadataObject::descriptionLocalizations)?.let { LocalizationMap.from(it) }
    )
}