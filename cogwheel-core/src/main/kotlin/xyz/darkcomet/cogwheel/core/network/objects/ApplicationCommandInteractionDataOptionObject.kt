package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ApplicationCommandInteractionDataOptionObject.Serializable::class)
data class ApplicationCommandInteractionDataOptionObject(
    val name: String,
    val type: Int,
    val value: Any? = null,
    val options: List<ApplicationCommandInteractionDataOptionObject>? = null,
    val focused: Boolean? = null
) {
    class Serializable : KSerializer<ApplicationCommandInteractionDataOptionObject> {
        
        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): ApplicationCommandInteractionDataOptionObject {
            TODO("Not yet implemented")
        }

        override fun serialize(
            encoder: Encoder,
            value: ApplicationCommandInteractionDataOptionObject
        ) {
            TODO("Not yet implemented")
        }

    }
}