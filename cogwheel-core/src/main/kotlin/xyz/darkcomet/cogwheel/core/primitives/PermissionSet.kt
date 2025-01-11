package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigInteger

@Serializable(with = PermissionSet.Serializer::class)
class PermissionSet
internal constructor(private val value: BigInteger) {
    
    infix fun and(permission: Permission): PermissionSet {
        return PermissionSet(value or permission.value)
    }

    infix fun and(otherSet: PermissionSet): PermissionSet {
        return PermissionSet(value or otherSet.value)
    }

    fun contains(permission: Permission): Boolean {
        return (value and permission.value) == permission.value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PermissionSet) {
            return false
        }
        
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.toString(10)
    }
    
    companion object {
        @JvmStatic fun from(vararg permissions: Permission): PermissionSet {
            return PermissionSet(permissions.fold(BigInteger.ZERO) { acc, permission -> acc or permission.value })
        }
        
        @JvmStatic fun all(): PermissionSet {
            return PermissionSet(Permission.entries.fold(BigInteger.ZERO) { acc, permission -> acc or permission.value })
        }
    }

    internal object Serializer : KSerializer<PermissionSet> {
        
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): PermissionSet {
            val rawValue = decoder.decodeString()
            val bitField = BigInteger(rawValue)
            return PermissionSet(bitField)
        }

        override fun serialize(encoder: Encoder, value: PermissionSet) {
            val decimalValue = value.value.toString(10)
            return encoder.encodeString(decimalValue)
        }
    }
}