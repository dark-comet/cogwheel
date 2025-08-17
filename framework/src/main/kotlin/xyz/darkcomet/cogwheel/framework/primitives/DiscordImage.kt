package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.ImageData

// TODO: decide how best to represent image hash (references) vs base64-encoded image data
class DiscordImage
private constructor(internal val data: ImageData) {
    
    companion object {
        @JvmStatic
        fun fromDataUriScheme(uriScheme: String): DiscordImage {
            TODO()
        }
        
        @JvmStatic
        fun fromImageData(imageData: ImageData): DiscordImage {
            TODO()
        }
    }
}