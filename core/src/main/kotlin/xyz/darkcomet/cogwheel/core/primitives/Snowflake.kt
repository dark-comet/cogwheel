package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigInteger

@Serializable(with = Snowflake.Serializer::class)
open class Snowflake(val value: BigInteger) : Comparable<Snowflake> {

    constructor(value: String) : this(BigInteger(value))
    constructor(value: Long) : this(BigInteger.valueOf(value))

    val timestampMs: Long = (value shr 22).toLong() + DISCORD_EPOCH_TIME_MS
    val workerId: Long = ((value and BigInteger("3E0000", 16)) shr 17).toLong()
    val processId: Long = ((value and BigInteger("1F000", 16)) shr 12).toLong()
    val increment: Long = (value and BigInteger("FFF", 16)).toLong()

    override fun equals(other: Any?): Boolean {
        if (other !is Snowflake) {
            return false
        }
        
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun compareTo(other: Snowflake): Int {
        return timestampMs.compareTo(other.timestampMs)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val DISCORD_EPOCH_TIME_MS = 1_420_070_400_000L
    }

    internal object Serializer : KSerializer<Snowflake> {
        
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): Snowflake {
            val string = decoder.decodeString()
            return Snowflake(string)
        }

        override fun serialize(encoder: Encoder, value: Snowflake) {
            val string = value.value.toString()
            encoder.encodeString(string)
        }
        
    }
}