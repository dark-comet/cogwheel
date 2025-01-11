package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Optional.Serializer::class)
open class Optional<T>(val value: T?) {
    
    val isNull: Boolean = value == null

    override fun equals(other: Any?): Boolean {
        if (other !is Optional<*>) {
            return false
        }
        
        return value == other.value
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

    override fun toString(): String {
        return value?.toString() ?: "null"
    }

    
    @OptIn(ExperimentalSerializationApi::class)
    internal class Serializer<T>(private val valueSerializer: KSerializer<T>) : KSerializer<Optional<T>> {
        
        override val descriptor: SerialDescriptor
            get() = valueSerializer.descriptor

        override fun deserialize(decoder: Decoder): Optional<T> {
            val value = decoder.decodeNullableSerializableValue(valueSerializer)
            return Optional(value)
        }

        override fun serialize(encoder: Encoder, value: Optional<T>) {
            encoder.encodeNullableSerializableValue(valueSerializer, value.value)
        }

    }
    
}