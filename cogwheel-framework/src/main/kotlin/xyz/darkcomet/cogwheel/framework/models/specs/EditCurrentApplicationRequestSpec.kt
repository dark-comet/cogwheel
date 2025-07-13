package xyz.darkcomet.cogwheel.framework.models.specs

import xyz.darkcomet.cogwheel.core.network.objects.ApplicationInstallParamsObject
import xyz.darkcomet.cogwheel.core.network.objects.ApplicationIntegrationTypeConfigurationObject
import xyz.darkcomet.cogwheel.core.network.objects.EditCurrentApplicationRequestParameters
import xyz.darkcomet.cogwheel.core.primitives.MaybeAbsent
import xyz.darkcomet.cogwheel.framework.models.DiscordImage
import xyz.darkcomet.cogwheel.framework.models.entitles.application.ApplicationInstallParameters
import xyz.darkcomet.cogwheel.framework.models.entitles.application.ApplicationIntegrationTypeConfiguration
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationFlag
import xyz.darkcomet.cogwheel.framework.primitives.ApplicationIntegrationType

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

    fun customInstallUrl(value: String) {
        this.customInstallUrl = MaybeAbsent(value)
    }
    
    fun description(value: String) {
        this.description = MaybeAbsent(value)
    }
    
    fun roleConnectionVerificationUrl(value: String) {
        this.roleConnectionVerificationUrl = MaybeAbsent(value)
    }
    
    fun installParams(value: ApplicationInstallParameters) {
        this.installParams = MaybeAbsent(value.toObject())
    }
    
    fun integrationTypesConfig(value: Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfiguration>) {
        val rawMap = HashMap<String, ApplicationIntegrationTypeConfigurationObject>()
        
        value.entries.forEach { 
            val rawType = it.key.key.toString()
            val rawConfigObj = it.value.toObject()
            rawMap.put(rawType, rawConfigObj)
        }
        
        this.integrationTypesConfig = MaybeAbsent(rawMap)
    }
    
    fun flags(value: ApplicationFlag) {
        this.flags = MaybeAbsent(value.key)
    }
    
    fun icon(value: DiscordImage?) {
        this.icon = MaybeAbsent(value?.imageData?.hash)
    }
    
    fun coverImage(value: DiscordImage) {
        this.coverImage = MaybeAbsent(value.imageData.hash)
    }
    
    fun interactionEndpointUrl(value: String) {
        this.interactionsEndpointUrl = MaybeAbsent(value)
    }
    
    fun tags(value: List<String>) {
        val valueCopy = ArrayList(value)
        this.tags = MaybeAbsent(valueCopy)
    }
    
    fun eventWebhooksUrl(value: String) {
        this.eventWebhooksUrl = MaybeAbsent(value)
    }
    
    fun eventWebhooksStatus(value: Int) {
        this.eventWebhooksStatus = MaybeAbsent(value)
    }
    
    fun eventWebhooksTypes(value: List<String>) {
        val valueCopy = ArrayList(value)
        this.eventWebhooksTypes = MaybeAbsent(valueCopy)
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