package xyz.darkcomet.cogwheel.core.network.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = AuditLogChangeObject.Serializer::class)
data class AuditLogChangeObject(
    @SerialName("new_value") val newValue: Any? = null,
    @SerialName("old_value") val oldValue: Any? = null,
    val key: String
) {
    
    internal class Serializer : KSerializer<AuditLogChangeObject> {
        
        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): AuditLogChangeObject {
            TODO("Not yet implemented")
        }

        override fun serialize(encoder: Encoder, value: AuditLogChangeObject) {
            TODO("Not yet implemented")
        }

    }
    
}