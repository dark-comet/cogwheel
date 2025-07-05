package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.primitives.ImageData

class DiscordImage
internal constructor(internal val imageData: ImageData) {
    companion object {
        @JvmStatic
        fun fromImageHash(imageHash: String): DiscordImage {
            TODO()
        }
    }
}