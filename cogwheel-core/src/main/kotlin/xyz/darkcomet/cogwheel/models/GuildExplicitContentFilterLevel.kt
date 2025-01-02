package xyz.darkcomet.cogwheel.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GuildExplicitContentFilterLevel.Serializer::class)
enum class GuildExplicitContentFilterLevel(val value: Int) {
    DISABLED(0),
    MEMBERS_WITHOUT_ROLES(1),
    ALL_MEMBERS(2)
    
    ;

    class Serializer : KSerializer<GuildExplicitContentFilterLevel?> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.INT)

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): GuildExplicitContentFilterLevel? {
            if (decoder.decodeNotNullMark()) {
                val value = decoder.decodeInt()
                return GuildExplicitContentFilterLevel.entries.find { it.value == value }
            } else {
                return null
            }
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: GuildExplicitContentFilterLevel?) {
            if (value == null) {
                encoder.encodeNull()
            } else {
                encoder.encodeInt(value.value)
            }
        }

    }
}