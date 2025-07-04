package xyz.darkcomet.cogwheel.framework.models.request

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.network.objects.EditCurrentApplicationRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.ImageData
import xyz.darkcomet.cogwheel.core.primitives.Possible
import xyz.darkcomet.cogwheel.framework.models.ApplicationInstallParameters
import xyz.darkcomet.cogwheel.framework.models.ApplicationIntegrationTypeConfiguration
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationIntegrationType
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationPublicFlags

class EditCurrentApplicationRequestSpec 
internal constructor() {
    
    private var customInstallUrl: Possible<String>? = null
    private var description: Possible<String>? = null
    private var roleConnectionVerificationUrl: Possible<String>? = null
    private var installParams: Possible<ApplicationInstallParamsObject>? = null
    private var integrationTypesConfig: Possible<Map<String, ApplicationIntegrationTypeConfigurationObject>>? = null
    private var flags: Possible<Int>? = null
    private var icon: Possible<ImageData?>? = null
    private var coverImage: Possible<ImageData?>? = null
    private var interactionsEndpointUrl: Possible<String>? = null
    private var tags: Possible<List<String>>? = null
    private var eventWebhooksUrl: Possible<String>? = null
    private var eventWebhooksStatus: Possible<Int>? = null
    private var eventWebhooksTypes: Possible<List<String>>? = null

    fun customInstallUrl(value: String) {
        this.customInstallUrl = Possible(value)
    }
    
    fun description(value: String) {
        this.description = Possible(value)
    }
    
    fun roleConnectionVerificationUrl(value: String) {
        this.roleConnectionVerificationUrl = Possible(value)
    }
    
    fun installParams(value: ApplicationInstallParameters) {
        this.installParams = Possible(value.toObject())
    }
    
    fun integrationTypesConfig(value: Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>) {
        val rawMap = HashMap<String, ApplicationIntegrationTypeConfigurationObject>()
        
        value.entries.forEach { 
            val rawType = it.key.type
            val rawConfigObj = it.value.toObject()
            rawMap.put(rawType, rawConfigObj)
        }
        
        this.integrationTypesConfig = Possible(rawMap)
    }
    
    fun flags(value: ApplicationPublicFlags) {
        this.flags = Possible(value.value)
    }
    
    fun icon(value: DiscordImage?) {
        this.icon = Possible(value?.imageData)
    }
    
    fun coverImage(value: DiscordImage) {
        this.coverImage = Possible(value.imageData)
    }
    
    fun interactionEndpointUrl(value: String) {
        this.interactionsEndpointUrl = Possible(value);
    }
    
    fun tags(value: List<String>) {
        val valueCopy = ArrayList(value);
        this.tags = Possible(valueCopy)
    }
    
    fun eventWebhooksUrl(value: String) {
        this.eventWebhooksUrl = Possible(value);
    }
    
    fun eventWebhooksStatus(value: Int) {
        this.eventWebhooksStatus = Possible(value)
    }
    
    fun eventWebhooksTypes(value: List<String>) {
        val valueCopy = ArrayList(value);
        this.eventWebhooksTypes = Possible(valueCopy);
    }
    
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