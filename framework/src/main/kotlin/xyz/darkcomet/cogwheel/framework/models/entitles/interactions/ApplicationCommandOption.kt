package xyz.darkcomet.cogwheel.framework.models.entitles.interactions

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandOptionObject
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationCommandOptionType
import xyz.darkcomet.cogwheel.framework.primitives.ChannelType
import xyz.darkcomet.cogwheel.framework.primitives.LocalizationMap

data class ApplicationCommandOption(
    val type: ApplicationCommandOptionType,
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val description: String,
    val descriptionLocalizations: LocalizationMap?,
    val required: Boolean?,
    val choices: List<ApplicationCommandOptionChoice>?,
    val options: List<ApplicationCommandOption>?,
    val channelTypes: List<ChannelType>?,
    val minValue: Double,
    val maxValue: Double,
    val minLength: Int,
    val maxLength: Int,
    val autoComplete: Boolean?
) {
    companion object {
        internal fun from(obj: ApplicationCommandOptionObject): ApplicationCommandOption {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandOptionObject.toModel(): ApplicationCommandOption {
    TODO()
}