package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.darkcomet.cogwheel.core.primitives.Snowflake

@Serializable
data class ApplicationCommandObject(
    val id: Snowflake,
    val type: Int? = null,
    @SerialName("application_id") val applicationId: Snowflake,
    @SerialName("guild_id") val guildId: Snowflake? = null,
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val description: String,
    @SerialName("description_localizations") val descriptionLocalizations: Map<String, String>? = null,
    val options: List<ApplicationCommandOptionObject>? = null,
    @SerialName("default_member_permissions") val defaultMemberPermissions: String? = null,
    @SerialName("dm_permission") val dmPermission: Boolean? = null,
    @SerialName("default_permission") val defaultPermission: Boolean? = null,
    val nsfw: Boolean? = null,
    @SerialName("integration_types") val integrationTypes: List<Int>? = null,
    val contexts: List<Int>? = null,
    val version: Snowflake,
    val handler: Int? = null
) {
    
    fun validate() {
        validateName(name)
        
        if (nameLocalizations != null) {
            validateLocalization("name", nameLocalizations, MAX_NAME_LENGTH)
        }
        
        validateDescription(type ?: 1, description)
        
        if (descriptionLocalizations != null) {
            validateLocalization("description", descriptionLocalizations, MAX_DESCRIPTION_LENGTH)
        }
        
        if ((options?.size ?: 0) > MAX_OPTIONS_SIZE) {
            throw IllegalArgumentException("options must not exceed $MAX_OPTIONS_SIZE entries")
        }

        options?.forEach { it.validate() }
    }
    
    companion object {
        
        const val MAX_NAME_LENGTH = 32
        const val MAX_DESCRIPTION_LENGTH = 100
        const val MAX_OPTIONS_SIZE = 25
        
        fun validateName(name: String) {
            if (name.isEmpty() || name.length > MAX_NAME_LENGTH) {
                throw IllegalArgumentException("name must be 1-${MAX_NAME_LENGTH} characters long")
            }
        }
        
        fun validateDescription(type: Int, description: String) {
            if (type == 1) {
                validateDescription(description)
            } else {
                if (description.isNotEmpty()) {
                    throw IllegalArgumentException("description must be empty for non-CHAT_INPUT type commands")
                }
            }
        }

        fun validateDescription(description: String) {
            if (description.isEmpty() || description.length > MAX_DESCRIPTION_LENGTH) {
                throw IllegalArgumentException("description must be 1-${MAX_DESCRIPTION_LENGTH} characters long for CHAT_INPUT type commands")
            }
        }

        fun validateLocalization(fieldName: String, localizationMap: Map<String, String>, maxLength: Int) {
            localizationMap.entries.forEach {
                val locale = it.key
                val localizedText = it.value

                if (localizedText.isEmpty() || localizedText.length > maxLength) {
                    throw IllegalArgumentException("localized $fieldName for locale '${locale}' must be 1-${maxLength} characters long, got ${localizedText.length}")
                }
            }
        }
    }
}