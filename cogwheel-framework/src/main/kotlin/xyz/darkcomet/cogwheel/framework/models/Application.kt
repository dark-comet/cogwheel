package xyz.darkcomet.cogwheel.framework.models

import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationEventWebhookStatus
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationId
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationIntegrationType
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationPublicFlags
import xyz.darkcomet.cogwheel.framework.primitives.GuildId
import xyz.darkcomet.cogwheel.framework.primitives.SkuId

class Application(
    val id: ApplicationId,
    val name: String,
    val icon: String?,
    val description: String,
    val rpcOrigins: Possible<Array<String>>?,
    val botPublic: Boolean,
    val botRequireCodeGrant: Boolean,
    val bot: Possible<PartialUser>,
    val termsOfServiceUrl: Possible<String>,
    val owner: Possible<PartialUser>?,
    val verifyKey: String,
    val team: Team?,
    val guildId: Possible<GuildId>?,
    val guild: Possible<PartialGuild>?,
    val primarySkuId: SkuId,
    val slug: Possible<String>?,
    val coverImage: Possible<DiscordImage>,
    val flags: Possible<ApplicationPublicFlags>,
    val approximateGuildCount: Possible<Int>?,
    val approximateUserInstallCount: Possible<Int>?,
    val approximateUserAuthorizationCount: Possible<Int>?,
    val redirectUrls: Possible<Array<String>>,
    val interactionsEndpointUrl: Possible<String?>?,
    val roleConnectionsVerificationUrl: Possible<String?>?,
    val eventWebhooksUrl: Possible<String?>?,
    val eventWebhooksStatus: ApplicationEventWebhookStatus,
    val eventWebhooksTypes: Possible<Array<String>>,
    val tags: Possible<Array<String>>,
    val installParams: Possible<ApplicationInstallParameters>,
    val integrationTypesConfig: Possible<Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>>,
    val customInstallUrl: Possible<String>?,
)