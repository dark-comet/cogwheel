@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.network.objects.EditCurrentApplicationRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.primitives.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.entitles.application.ApplicationInstallParameters
import xyz.darkcomet.cogwheel.framework.models.entitles.application.ApplicationIntegrationTypeConfiguration
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationEventWebhookStatus
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationFlag
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationIntegrationType
import xyz.darkcomet.cogwheel.framework.primitives.MapBuilder
import xyz.darkcomet.cogwheel.framework.primitives.WebhookEventType
import java.util.function.Consumer

class EditCurrentApplicationRequestSpec 
internal constructor() {
    
    private var customInstallUrl: MaybeAbsent<String>? = null
    private var description: MaybeAbsent<String>? = null
    private var roleConnectionVerificationUrl: MaybeAbsent<String>? = null
    private var installParams: MaybeAbsent<ApplicationInstallParamsObject>? = null
    private var integrationTypesConfig: MaybeAbsent<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null
    private var flags: MaybeAbsent<Int>? = null
    private var icon: MaybeAbsent<String?>? = null
    private var coverImage: MaybeAbsent<String?>? = null
    private var interactionsEndpointUrl: MaybeAbsent<String>? = null
    private var tags: MaybeAbsent<List<String>>? = null
    private var eventWebhooksUrl: MaybeAbsent<String>? = null
    private var eventWebhooksStatus: MaybeAbsent<Int>? = null
    private var eventWebhooksTypes: MaybeAbsent<List<String>>? = null

    fun customInstallUrl(value: String) : EditCurrentApplicationRequestSpec
        = apply { this.customInstallUrl = MaybeAbsent(value) }
    
    fun description(value: String) : EditCurrentApplicationRequestSpec
        = apply { this.description = MaybeAbsent(value) }
    
    fun roleConnectionVerificationUrl(value: String) : EditCurrentApplicationRequestSpec
        = apply { this.roleConnectionVerificationUrl = MaybeAbsent(value) }
    
    fun installParams(value: ApplicationInstallParameters) : EditCurrentApplicationRequestSpec
        = apply { this.installParams = MaybeAbsent(value.toObject()) }
    
    fun installParams(paramsBuilder: ApplicationInstallParameters.BuilderSpec.() -> Unit) : EditCurrentApplicationRequestSpec {
        val builder = ApplicationInstallParameters.builder()
        paramsBuilder.invoke(builder)
        this.installParams = MaybeAbsent(builder.build().toObject())
        
        return this
    }
    
    fun installParams(paramsBuilder: Consumer<ApplicationInstallParameters.BuilderSpec>) : EditCurrentApplicationRequestSpec {
        val builder = ApplicationInstallParameters.builder()
        paramsBuilder.accept(builder)
        this.installParams = MaybeAbsent(builder.build().toObject())
        
        return this
    }
    
    fun integrationTypesConfig(map: Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>) : EditCurrentApplicationRequestSpec
        = apply { this.integrationTypesConfig = integrationTypesConfigRaw(map) }

    private fun integrationTypesConfigRaw(
        map: Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>
    ): MaybeAbsent<Map<String, ApplicationIntegrationTypeConfigurationObject>> {
        
        val rawMap = HashMap<String, ApplicationIntegrationTypeConfigurationObject>()

        map.entries.forEach {
            val rawType = it.key.key.toString()
            val rawConfigObj = it.value.toObject()
            rawMap.put(rawType, rawConfigObj)
        }
        
        return MaybeAbsent(rawMap) 
    }

    fun integrationTypesConfig(
        mapBuilder: Consumer<MapBuilder<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>>
    ) : EditCurrentApplicationRequestSpec {
        
        val map = MapBuilder<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>()
        mapBuilder.accept(map)
        this.integrationTypesConfig = integrationTypesConfigRaw(map.build())
        
        return this
    }
    
    fun integrationTypesConfig(
        mapBuilder: MapBuilder<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>.() -> Unit
    ) : EditCurrentApplicationRequestSpec {
        
        val map = MapBuilder<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>()
        mapBuilder.invoke(map)
        this.integrationTypesConfig = integrationTypesConfigRaw(map.build())
        
        return this
    }
    
    fun flags(value: ApplicationFlag) : EditCurrentApplicationRequestSpec
        = apply { this.flags = MaybeAbsent(value.key) }
    
    fun icon(value: DiscordImage?) : EditCurrentApplicationRequestSpec
        = apply { this.icon = MaybeAbsent(value?.data?.hash) }
    
    fun coverImage(value: DiscordImage) : EditCurrentApplicationRequestSpec
        = apply { this.coverImage = MaybeAbsent(value.data.hash) }
    
    fun interactionEndpointUrl(value: String) : EditCurrentApplicationRequestSpec 
        = apply { this.interactionsEndpointUrl = MaybeAbsent(value) }
    
    fun tags(value: List<String>) : EditCurrentApplicationRequestSpec
        = apply { this.tags = MaybeAbsent(ArrayList(value)) }
    
    fun tags(vararg values: String) : EditCurrentApplicationRequestSpec
        = apply { this.tags = MaybeAbsent(values.toList()) }
    
    fun eventWebhooksUrl(url: String) : EditCurrentApplicationRequestSpec
        = apply { this.eventWebhooksUrl = MaybeAbsent(url) }
    
    fun eventWebhooksStatus(status: ApplicationEventWebhookStatus) : EditCurrentApplicationRequestSpec 
        = apply { this.eventWebhooksStatus = MaybeAbsent(status.key) }
    
    fun eventWebhooksTypes(types: List<WebhookEventType>) : EditCurrentApplicationRequestSpec
        = apply { this.eventWebhooksTypes = MaybeAbsent(types.map { it.key }) }
    
    fun eventWebhookTypes(vararg types: WebhookEventType) : EditCurrentApplicationRequestSpec
        = apply { this.eventWebhooksTypes = MaybeAbsent(types.toList().map { it.key }) }
    
    internal fun build(): EditCurrentApplicationRequestParameters {
        return EditCurrentApplicationRequestParameters(
            customInstallUrl,
            description,
            roleConnectionVerificationUrl,
            installParams,
            integrationTypesConfig,
            flags,
            icon,
            coverImage,
            interactionsEndpointUrl,
            tags,
            eventWebhooksUrl,
            eventWebhooksStatus,
            eventWebhooksTypes
        )
    }
}