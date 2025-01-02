package xyz.darkcomet.cogwheel.core.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GuildDefaultMessageNotificationLevel.Serializer::class)
enum class GuildDefaultMessageNotificationLevel(val value: Int) {
    ALL_MESSAGES(0),
    ONLY_MENTIONS(1)
    
    ;

    class Serializer : KSerializer<GuildDefaultMessageNotificationLevel?> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.INT)

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): GuildDefaultMessageNotificationLevel? {
            if (decoder.decodeNotNullMark()) {
                val value = decoder.decodeInt()
                return GuildDefaultMessageNotificationLevel.entries.find { it.value == value }
            } else {
                return null
            }
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: GuildDefaultMessageNotificationLevel?) {
            if (value == null) {
                encoder.encodeNull()
            } else {
                encoder.encodeInt(value.value)
            }
        }

    }
}