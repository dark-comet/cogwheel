package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationCommandOptionObject(
    val type: Int,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null,
    val required: Boolean? = null,
    val choices: List<ApplicationCommandOptionChoiceObject>? = null,
    val options: List<ApplicationCommandOptionObject>? = null,
    @SerialName("channel_types") val channelTypes: List<Int>? = null,
    @SerialName("min_value") val minValue: Double? = null,
    @SerialName("max_value") val maxValue: Double? = null,
    @SerialName("min_length") val minLength: Int? = null,
    @SerialName("max_length") val maxLength: Int? = null,
    val autocomplete: Boolean? = null
)