@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.SkuObject
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.*

class Sku(
    id: SkuId,
    type: SkuType,
    applicationId: ApplicationId,
    name: String,
    slug: String,
    flags: BitField<SkuFlag>
) {
    companion object {
        internal fun from(obj: SkuObject): Sku {
            return obj.toModel()
        }
    }
}

internal fun SkuObject.toModel(): Sku {
    return Sku(
        id = requireNonNull(this, SkuObject::id).asSkuId(),
        type = requireNonNull(this, SkuObject::type).let { SkuType.fromOrAdd(it) },
        applicationId = requireNonNull(this, SkuObject::applicationId).asApplicationId(),
        name = requireNonNull(this, SkuObject::name),
        slug = requireNonNull(this, SkuObject::slug),
        flags = requireNonNull(this, SkuObject::flags).let { BitField.from(it) }
    )
}