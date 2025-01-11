package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Value.Serializer::class)
class Value<T> (value: T) : NullableValue<T>(value) {
    
    internal class Serializer<T>(private val valueSerializer: KSerializer<T>) : KSerializer<Value<T>> {

        override val descriptor: SerialDescriptor
            get() = valueSerializer.descriptor

        override fun deserialize(decoder: Decoder): Value<T> {
            val value = decoder.decodeSerializableValue(valueSerializer)
            return Value(value)
        }

        override fun serialize(encoder: Encoder, value: Value<T>) {
            val toEncode = value.value ?: throw IllegalStateException("Value is null")
            encoder.encodeSerializableValue(valueSerializer, toEncode)
        }

    }
    
}