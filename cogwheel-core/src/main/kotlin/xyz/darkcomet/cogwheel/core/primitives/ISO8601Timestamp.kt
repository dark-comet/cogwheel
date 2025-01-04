package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ISO8601Timestamp.Serializer::class)
class ISO8601Timestamp(private val instant: Instant) {
    
    val stringValue = instant.toString()
    val epochMilliseconds = instant.toEpochMilliseconds()
    
    class Serializer : KSerializer<ISO8601Timestamp> {
        
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.java.name, PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): ISO8601Timestamp {
            return fromFormattedString(decoder.decodeString())
        }

        override fun serialize(encoder: Encoder, value: ISO8601Timestamp) {
            encoder.encodeString(value.stringValue)
        }
        
    }
    
    companion object {
        
        fun fromEpochMilliseconds(epochMilliseconds: Long): ISO8601Timestamp {
            return ISO8601Timestamp(Instant.fromEpochMilliseconds(epochMilliseconds))
        }
        
        fun fromFormattedString(string: String): ISO8601Timestamp {
            return ISO8601Timestamp(Instant.parse(string))
        }
        
    }
    
}