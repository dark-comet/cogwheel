package xyz.darkcomet.cogwheel.framework.models.entitles.interactions

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationCommandObject
import xyz.darkcomet.cogwheel.core.primitives.Snowflake
import xyz.darkcomet.cogwheel.framework.models.require
import xyz.darkcomet.cogwheel.framework.models.requireNonNull
import xyz.darkcomet.cogwheel.framework.models.requireNonNullIfPresent
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
    val defaultMemberPermissions: BitField<Permission>?,
    val dmPermission: Boolean?,
    val defaultPermission: Boolean?,
    val nsfw: Boolean?,
    val integrationTypes: List<ApplicationIntegrationType>?,
    val contexts: List<InteractionContextType>?,
    val version: Snowflake,
    val handler: ApplicationCommandHandlerType?
) {
    
    companion object {
        internal fun from(obj: ApplicationCommandObject): ApplicationCommand {
            return obj.toModel()
        }
    }
}

internal fun ApplicationCommandObject.toModel(): ApplicationCommand {
    return ApplicationCommand(
        id = requireNonNull(this, ApplicationCommandObject::id).asApplicationCommandId(),
        type = requireNonNullIfPresent(this, ApplicationCommandObject::type)?.let { ApplicationCommandType.fromOrAdd(it) } ?: ApplicationCommandType.CHAT_INPUT,
        applicationId = requireNonNull(this, ApplicationCommandObject::applicationId).asApplicationId(),
        guildId = requireNonNullIfPresent(this, ApplicationCommandObject::guildId)?.asGuildId(),
        name = requireNonNull(this, ApplicationCommandObject::name),
        nameLocalizations = requireNonNullIfPresent(this, ApplicationCommandObject::nameLocalizations)?.let { LocalizationMap.from(it) },
        description = requireNonNull(this, ApplicationCommandObject::description),
        descriptionLocalizations = requireNonNullIfPresent(this, ApplicationCommandObject::descriptionLocalizations)?.let { LocalizationMap.from(it) },
        options = requireNonNullIfPresent(this, ApplicationCommandObject::options)?.map { it.toModel() },
        defaultMemberPermissions = require(this, ApplicationCommandObject::defaultMemberPermissions)?.let { BitField.from(it) },
        dmPermission = requireNonNullIfPresent(this, ApplicationCommandObject::dmPermission),
        defaultPermission = this.defaultPermission?.value,
        nsfw = requireNonNullIfPresent(this, ApplicationCommandObject::nsfw),
        integrationTypes = requireNonNullIfPresent(this, ApplicationCommandObject::integrationTypes)?.map { ApplicationIntegrationType.fromOrAdd(it) },
        contexts = this.contexts?.value?.map { InteractionContextType.fromOrAdd(it) },
        version = requireNonNull(this, ApplicationCommandObject::version),
        handler = requireNonNullIfPresent(this, ApplicationCommandObject::handler)?.let { ApplicationCommandHandlerType.fromOrAdd(it) }
    )
}