package xyz.darkcomet.cogwheel.framework.primitives

import xyz.darkcomet.cogwheel.core.primitives.ImageData

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