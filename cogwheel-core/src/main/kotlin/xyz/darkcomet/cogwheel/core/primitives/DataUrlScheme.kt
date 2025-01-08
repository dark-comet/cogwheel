package xyz.darkcomet.cogwheel.core.primitives

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.extension

@Serializable(with = DataUrlScheme.Serializer::class)
sealed class DataUrlScheme {

    abstract val mediaType: String
    abstract val isBase64: Boolean
    abstract val data: String
    
    class Serializer : KSerializer<DataUrlScheme> {
        
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): DataUrlScheme {
            val rawValue = decoder.decodeString()
            val parts = rawValue.split(",")
            
            if (parts.size != 2) {
                throw IllegalArgumentException("Malformed DataUrlScheme value (${parts.size} parts instead of 2): $rawValue")
            }
            
            val descriptors = parts[0].split(";")
            
            val mediaType = descriptors[0].removePrefix("data:")
//            val isBase64 = descriptors.last().lowercase() == "base64"
            val data = parts[1]
            
            if (mediaType.startsWith("image/")) {
                return ImageData.fromMediaType(mediaType, data)
            } else {
                throw IllegalArgumentException("Unsupported DataUrlScheme media type: $mediaType")
            }
        }

        override fun serialize(encoder: Encoder, value: DataUrlScheme) {
            val serializedData = StringBuilder().apply {
                append("data:")
                append(value.mediaType)
                if (value.isBase64) {
                    append(";base64")
                }
                append(",")
                append(value.data)
            }.toString()
            
            encoder.encodeString(serializedData)
        }

    }
    
}

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE", "ConvertSecondaryConstructorToPrimary")
@Serializable(with = DataUrlScheme.Serializer::class)
class ImageData : DataUrlScheme {

    override val mediaType: String
    override val isBase64 = true
    override val data: String
    
    private constructor(mediaType: String, base64Data: String) : super() {
        this.mediaType = mediaType
        data = base64Data
    }

    companion object {
        const val MIME_TYPE_PROTOTYPE = "image/"
        
        @JvmStatic 
        fun fromExtension(extension: FileExtension, base64Data: String): ImageData {
            if (FileExtension.entries.none { it == extension }) {
                throw IllegalArgumentException("Unsupported ImageData file extension: $extension")
            }
            return ImageData("$MIME_TYPE_PROTOTYPE${extension.value}", base64Data)
        }
        
        @JvmStatic
        fun fromMediaType(mediaType: String, base64Data: String): ImageData {
            if (FileExtension.entries.none { mediaType == MIME_TYPE_PROTOTYPE + it.value }) {
                throw IllegalArgumentException("Unsupported ImageData MIME type: $mediaType")
            }
            return ImageData(mediaType, base64Data)
        }
        
        @JvmStatic
        fun fromFile(path: Path): ImageData {
            if (!Files.isRegularFile(path)) {
                throw IllegalArgumentException("Path does not refer to a regular file: ${path.toAbsolutePath()}") 
            }
            
            val rawExtension = path.extension
            val matchedExtension = FileExtension.entries.firstOrNull { it.value == rawExtension }
            
            if (matchedExtension == null) {
                throw IllegalArgumentException("Unsupported file extension for ImageData: $rawExtension")
            }
            
            val rawBytes = Files.readAllBytes(path)
            return fromBytes(matchedExtension, rawBytes)
        }
        
        @JvmStatic
        fun fromBytes(extension: FileExtension, bytes: ByteArray): ImageData {
            val base64Data = Base64.getEncoder().encodeToString(bytes)
            return fromExtension(extension, base64Data)
        }
    }
    
    enum class FileExtension(val value: String) {
        PNG("png"),
        JPG("jpg"),
        GIF("gif")
    }
}