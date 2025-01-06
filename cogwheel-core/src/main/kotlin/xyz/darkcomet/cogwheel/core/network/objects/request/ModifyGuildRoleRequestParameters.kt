package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Permissions

@Serializable
data class ModifyGuildRoleRequestParameters(
    val name: String? = null,
    val permissions: String? = null,
    val color: Int? = null,
    val hoist: Boolean? = null,
    val icon: ImageData? = null,
    @SerialName("unicode_emoji") val unicodeEmoji: String? = null,
    val mentionable: Boolean? = null
)