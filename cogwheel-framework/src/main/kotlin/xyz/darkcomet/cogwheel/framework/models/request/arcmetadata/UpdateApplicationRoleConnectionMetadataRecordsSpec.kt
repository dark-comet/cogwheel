package xyz.darkcomet.cogwheel.framework.models.request.arcmetadata

import xyz.darkcomet.cogwheel.framework.models.entitles.arcmetadata.ApplicationRoleConnectionMetadata
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import java.util.ArrayList

class UpdateApplicationRoleConnectionMetadataRecordsSpec {
    var id: ApplicationId? = null
    var metadataRecords: List<ApplicationRoleConnectionMetadata>? = ArrayList(4)
}