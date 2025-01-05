package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.ISO8601Timestamp

@Serializable
data class PollObject(
    val question: PollMediaObject,
    val answers: List<PollAnswerObject>,
    val expiry: ISO8601Timestamp? = null,
    @SerialName("allow_multiselect") val allowMultiselect: Boolean,
    @SerialName("layout_type") val layoutType: Int,
    val results: PollResultsObject? = null
)