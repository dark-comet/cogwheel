package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ModifyGuildSoundboardSoundRequestParameters(
    val name: String? = null,
    val volume: Double? = null,
    @SerialName("emoji_id") val emojiId: Snowflake? = null,
    @SerialName("emoji_name") val emojiName: String? = null
)