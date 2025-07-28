package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateTestEntitlementRequestParameters
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EntitlementOwnerType

class CreateTestEntitlementRequestSpec(
    internal val applicationId: ApplicationId
) {
    
    internal var skuId: String? = null
    internal var ownerId: String? = null
    internal var ownerType: EntitlementOwnerType? = null
    
    internal fun buildParameters(): CreateTestEntitlementRequestParameters {
        return CreateTestEntitlementRequestParameters(
            skuId = this.skuId ?: throw InvalidModelException("'skuId' is required"),
            ownerId = this.ownerId ?: throw InvalidModelException("'ownerId' is required"),
            ownerType = this.ownerType?.key ?: throw InvalidModelException("'ownerType' is required"),
        )
    }
    
}