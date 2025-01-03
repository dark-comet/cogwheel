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
) {
    
    fun validate() {
        ApplicationCommandObject.validateName(name)
        
        if (nameLocalizations != null) {
            ApplicationCommandObject.validateLocalization("name", nameLocalizations, ApplicationCommandObject.MAX_NAME_LENGTH)
        }
        
        ApplicationCommandObject.validateDescription(description)
        
        if (descriptionLocalizations != null) {
            ApplicationCommandObject.validateLocalization("description", descriptionLocalizations, ApplicationCommandObject.MAX_DESCRIPTION_LENGTH)
        }
        
        if (maxValue != null && minValue != null && maxValue < minValue) {
            throw IllegalArgumentException("maxValue cannot be less than minValue")
        }
        
        if (minLength != null && maxLength != null && maxLength < minLength) {
            throw IllegalArgumentException("maxLength cannot be less than minLength")
        }
        
        choices?.forEach { it.validate() }
        options?.forEach { it.validate() }
    }
    
}