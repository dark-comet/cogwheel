package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ModifyApplicationEmojiRequestParameters
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.EmojiId

class ModifyApplicationEmojiRequestSpec(
    internal val appId: ApplicationId,
    internal val emojiId: EmojiId,
) {
    
    internal var name: String? = null
    internal var image: String? = null
    
    fun name(name: String): ModifyApplicationEmojiRequestSpec 
        = apply { this.name = name }
    
    fun image(image: DiscordImage): ModifyApplicationEmojiRequestSpec
        = apply { this.image = image.data.toString() }
    
    internal fun buildParameters(): ModifyApplicationEmojiRequestParameters {
        return ModifyApplicationEmojiRequestParameters(
            name = this.name ?: throw InvalidModelException("'name' is required"),
            image = this.image ?: throw InvalidModelException("'image' is required") 
        )
    }
}