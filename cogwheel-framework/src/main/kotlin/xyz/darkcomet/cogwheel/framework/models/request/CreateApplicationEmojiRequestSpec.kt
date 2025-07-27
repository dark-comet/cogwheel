package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.CreateApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId

class CreateApplicationEmojiRequestSpec(internal var appId: ApplicationId) {
    
    internal var name: String? = null
    internal var image: ImageData? = null
    
    fun name(name: String): CreateApplicationEmojiRequestSpec
        = apply { this.name = name }
    
    fun image(image: DiscordImage): CreateApplicationEmojiRequestSpec
        = apply { this.image = image.data }
    
    internal fun buildParameters(): CreateApplicationEmojiRequestParameters {
        return CreateApplicationEmojiRequestParameters(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            image = this.image?.toString() ?: throw InvalidModelException("'image' is required"),
        )
    }
    
}