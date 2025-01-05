package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Permissions

@Serializable
data class CreateGuildRoleRequestParameters(
    val name: String,
    val permissions: Permissions,
    val color: Int,
    val hoist: Boolean,
    val icon: ImageData?,
    @SerialName("unicode_emoji") val unicodeEmoji: String?,
    val mentionable: Boolean
)