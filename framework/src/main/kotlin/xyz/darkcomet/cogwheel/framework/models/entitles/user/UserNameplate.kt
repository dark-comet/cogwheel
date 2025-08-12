package xyz.darkcomet.cogwheel.framework.models.entitles.user

import xyz.darkcomet.cogwheel.core.network.objects.UserNameplateObject
import xyz.darkcomet.cogwheel.core.primitives.AssetLocation
import xyz.darkcomet.cogwheel.framework.utils.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.UserNameplatePalette
import xyz.darkcomet.cogwheel.framework.primitives.asSkuId

data class UserNameplate(
    val skuId: SkuId,
    val asset: AssetLocation,
    val label: String,
    val palette: UserNameplatePalette
) {
    companion object {
        internal fun from(obj: UserNameplateObject): UserNameplate {
            return obj.toModel();
        }
    }
}

internal fun UserNameplateObject.toModel(): UserNameplate {
    return UserNameplate(
        skuId = requireNonNull(this, UserNameplateObject::skuId)!!.asSkuId(),
        asset = requireNonNull(this, UserNameplateObject::asset)!!.let { AssetLocation(it) },
        label = requireNonNull(this, UserNameplateObject::label)!!,
        palette = requireNonNull(this, UserNameplateObject::palette)!!.let { UserNameplatePalette.fromOrAdd(it) }
    )
}