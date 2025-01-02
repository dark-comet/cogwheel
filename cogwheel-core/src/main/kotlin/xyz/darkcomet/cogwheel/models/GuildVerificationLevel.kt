package xyz.darkcomet.cogwheel.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GuildVerificationLevel.Serializer::class)
enum class GuildVerificationLevel(val value: Int) {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    VERY_HIGH(4)
    
    ;
    
    class Serializer : KSerializer<GuildVerificationLevel?> {
        
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.INT)

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): GuildVerificationLevel? {
            if (decoder.decodeNotNullMark()) {
                val value = decoder.decodeInt()
                return entries.find { it.value == value }    
            } else {
                return null
            }
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: GuildVerificationLevel?) {
            if (value == null) {
                encoder.encodeNull()
            } else {
                encoder.encodeInt(value.value)
            }
        }
        
    }
}