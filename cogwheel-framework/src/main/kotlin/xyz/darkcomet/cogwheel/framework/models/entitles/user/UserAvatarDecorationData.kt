package xyz.darkcomet.cogwheel.framework.models.entitles.user

import xyz.darkcomet.cogwheel.core.network.objects.UserAvatarDecorationDataObject
import xyz.darkcomet.cogwheel.framework.primitives.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.primitives.SkuId
import xyz.darkcomet.cogwheel.framework.primitives.asSkuId

data class UserAvatarDecorationData(
    val asset: DiscordImage,
    val skuId: SkuId
) {
    companion object {
        internal fun from(obj: UserAvatarDecorationDataObject): UserAvatarDecorationData {
            return obj.toModel()
        }
    }
}

internal fun UserAvatarDecorationDataObject.toModel(): UserAvatarDecorationData {
    return UserAvatarDecorationData(
        asset = requireNonNull(this, UserAvatarDecorationDataObject::asset).let { DiscordImage.fromDataUriScheme(it) },
        skuId = requireNonNull(this, UserAvatarDecorationDataObject::skuId).asSkuId()
    )
}