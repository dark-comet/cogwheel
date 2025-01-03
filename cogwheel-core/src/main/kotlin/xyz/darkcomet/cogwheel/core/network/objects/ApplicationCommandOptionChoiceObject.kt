package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ApplicationCommandOptionChoiceObject.Serializer::class)
class ApplicationCommandOptionChoiceObject(
    val name: String,
    @SerialName("name_localizations") val nameLocalizations: Map<String, String>? = null,
    val value: Any
) {
    
    fun validate() {
        val maxNameLength = 100
        
        if (name.isEmpty() || name.length > 100) {
            throw IllegalArgumentException("name must be 1-${maxNameLength} characters long")
        }
        
        if (nameLocalizations != null) {
            ApplicationCommandObject.validateLocalization("name", nameLocalizations, maxNameLength)
        }
        
        if (value !is String && value !is Int && value !is Double) {
            throw IllegalArgumentException("Invalid value type! Must be one of: String, Int, Double")
        }
    }
    
    internal class Serializer : KSerializer<ApplicationCommandOptionChoiceObject> {

        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): ApplicationCommandOptionChoiceObject {
            TODO("Not yet implemented")
        }

        override fun serialize(encoder: Encoder, value: ApplicationCommandOptionChoiceObject) {
            TODO("Not yet implemented")
        }

    }
    
}