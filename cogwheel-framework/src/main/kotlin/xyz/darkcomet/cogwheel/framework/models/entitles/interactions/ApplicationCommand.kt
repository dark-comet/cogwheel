package xyz.darkcomet.cogwheel.framework.models.entitles.interactions

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.Permissions
import xyz.darkcomet.cogwheel.framework.primitives.*

data class ApplicationCommand(
    val id: ApplicationCommandId,
    val type: ApplicationCommandType,
    val applicationId: ApplicationId,
    val guildId: GuildId?,
    val name: String,
    val nameLocalizations: LocalizationMap?,
    val description: String,
    val descriptionLocalizations: LocalizationMap?,
    val options: List<ApplicationCommandOption>?,
    val defaultMemberPermissions: Permissions,
    val dmPermission: Boolean?,
    val defaultPermission: Boolean?,
    val nsfw: Boolean?,
    val integrationTypes: List<ApplicationIntegrationType>?,
    val contexts: List<InteractionContextType>,
    val version: Snowflake,
    val handler: ApplicationCommandHandlerType
) {
    
    companion object {
        internal fun from(obj: ApplicationCommandObject): ApplicationCommand {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandObject.toModel(): ApplicationCommand {
    TODO()
}