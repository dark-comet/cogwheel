package xyz.darkcomet.cogwheel.framework.models.entitles.application

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationObject
import xyz.darkcomet.cogwheel.framework.exceptions.InvalidModelException
import xyz.darkcomet.cogwheel.framework.models.*
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.PartialGuild
import xyz.darkcomet.cogwheel.framework.models.entitles.guild.toPartialGuildModel
import xyz.darkcomet.cogwheel.framework.primitives.*

class Application(
    val id: ApplicationId,
    val name: String,
    val icon: String?,
    val description: String,
    val rpcOrigins: List<String>?,
    val botPublic: Boolean,
    val botRequireCodeGrant: Boolean,
    val bot: PartialUser?,
    val termsOfServiceUrl: String?,
    val privacyPolicyUrl: String?,
    val owner: PartialUser?,
    val verifyKey: String,
    val team: Team?,
    val guildId: GuildId?,
    val guild: PartialGuild?,
    val primarySkuId: SkuId?,
    val slug: String?,
    val coverImage: DiscordImage?,
    val flags: ApplicationFlag?,
    val approximateGuildCount: Int?,
    val approximateUserInstallCount: Int?,
    val approximateUserAuthorizationCount: Int?,
    val redirectUris: List<String>?,
    val interactionsEndpointUrl: String?,
    val roleConnectionsVerificationUrl: String?,
    val eventWebhooksUrl: String?,
    val eventWebhooksStatus: ApplicationEventWebhookStatus?,
    val eventWebhooksTypes: List<String>?,
    val tags: List<String>?,
    val installParams: ApplicationInstallParameters?,
    val integrationTypesConfig: Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>?,
    val customInstallUrl: String?,
)

internal fun ApplicationObject.toModel(): Application {
    return Application(
        id = requireNonNull(this, ApplicationObject::id).asApplicationId(),
        name = requireNonNull(this, ApplicationObject::name),
        icon = requireNonNull(this, ApplicationObject::icon),
        description = requireNonNull(this, ApplicationObject::description),
        rpcOrigins = requireNonNullIfPresent(this, ApplicationObject::rpcOrigins),
        botPublic = requireNonNull(this, ApplicationObject::botPublic)!!,
        botRequireCodeGrant = requireNonNull(this, ApplicationObject::botRequireCodeGrant),
        bot = requireNonNullIfPresent(this, ApplicationObject::bot)?.toPartialUserModel(),
        termsOfServiceUrl = requireNonNullIfPresent(this, ApplicationObject::termsOfServiceUrl),
        privacyPolicyUrl = requireNonNullIfPresent(this, ApplicationObject::privacyPolicyUrl),
        owner = requireNonNullIfPresent(this, ApplicationObject::owner)?.toPartialUserModel(),
        verifyKey = requireNonNull(this, ApplicationObject::verifyKey),
        team = require(this, ApplicationObject::team)?.toModel(),
        guildId = requireNonNullIfPresent(this, ApplicationObject::guildId)?.asGuildId(),
        guild = requireNonNullIfPresent(this, ApplicationObject::guild)?.toPartialGuildModel(),
        primarySkuId = requireNonNullIfPresent(this, ApplicationObject::primarySkuId)?.asSkuId(),
        slug = requireNonNullIfPresent(this, ApplicationObject::slug),
        coverImage = requireNonNullIfPresent(this, ApplicationObject::coverImage)?.let { DiscordImage.fromImageHash(it) },
        flags = requireNonNullIfPresent(this, ApplicationObject::flags)?.let { ApplicationFlag.fromOrAdd(it) },
        approximateGuildCount = requireNonNullIfPresent(this, ApplicationObject::approximateGuildCount),
        approximateUserInstallCount = requireNonNullIfPresent(this, ApplicationObject::approximateUserInstallCount),
        approximateUserAuthorizationCount = requireNonNullIfPresent(this, ApplicationObject::approximateUserAuthorizationCount),
        redirectUris = requireNonNullIfPresent(this, ApplicationObject::redirectUris),
        interactionsEndpointUrl = requireNonNullIfPresent(this, ApplicationObject::interactionsEndpointUrl),
        roleConnectionsVerificationUrl = requireNonNullIfPresent(this, ApplicationObject::roleConnectionsVerificationUrl),
        eventWebhooksUrl = requireNonNullIfPresent(this, ApplicationObject::eventWebhooksUrl),
        eventWebhooksStatus = requireNonNullIfPresent(this, ApplicationObject::eventWebhooksStatus)?.let { ApplicationEventWebhookStatus.fromOrAdd(it) },
        eventWebhooksTypes = requireNonNullIfPresent(this, ApplicationObject::eventWebhookTypes),
        tags = requireNonNullIfPresent(this, ApplicationObject::tags),
        installParams = requireNonNullIfPresent(this, ApplicationObject::installParams)?.toModel(),
        integrationTypesConfig = requireNonNullIfPresent(this, ApplicationObject::integrationTypesConfig)?.let { transformIntegrationTypesConfig(it) },
        customInstallUrl = requireNonNullIfPresent(this, ApplicationObject::customInstallUrl),
    )
}

private fun transformIntegrationTypesConfig(
    rawMap: Map<String, ApplicationIntegrationTypeConfigurationObject>
): Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration> {
    
    val result = mutableMapOf<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>()
    
    rawMap.entries.forEach { entry ->
        val keyAsInt: Int
        try {
            keyAsInt = entry.key.toInt()
        } catch (ex: NumberFormatException) {
            throw InvalidModelException("Unsupported integration type key: ${entry.key}", ex)
        }
        
        val mappedKey = ApplicationIntegrationType.fromOrAdd(keyAsInt)
        val mappedValue = entry.value.toModel()
        result.put(mappedKey, mappedValue)
    }
    
    return result
}