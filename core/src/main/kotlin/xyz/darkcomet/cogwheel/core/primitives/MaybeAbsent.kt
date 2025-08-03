package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents a value that may or may not be present in a transmitted network entity. A 'MaybeAbsent'
 * may hold 'null' as a value, which means a value is present representing 'null'. If a 'MaybeAbsent'
 * field is null, it represents the complete absence of the field.
 */
@Serializable(with = MaybeAbsent.Serializer::class)
open class MaybeAbsent<T>(val value: T?) {

    override fun equals(other: Any?): Boolean {
        if (other !is MaybeAbsent<*>) {
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
    internal class Serializer<T>(private val valueSerializer: KSerializer<T>) : KSerializer<MaybeAbsent<T>> {
        
        override val descriptor: SerialDescriptor
            get() = valueSerializer.descriptor

        override fun deserialize(decoder: Decoder): MaybeAbsent<T> {
            val value = decoder.decodeNullableSerializableValue(valueSerializer)
            return MaybeAbsent(value)
        }

        override fun serialize(encoder: Encoder, value: MaybeAbsent<T>) {
            encoder.encodeNullableSerializableValue(valueSerializer, value.value)
        }

    }
    
}