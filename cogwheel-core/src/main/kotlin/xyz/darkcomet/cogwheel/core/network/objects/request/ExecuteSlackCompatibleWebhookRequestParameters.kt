package xyz.darkcomet.cogwheel.core.network.objects.request

import kotlinx.serialization.KSerializer

interface ExecuteSlackCompatibleWebhookRequestParameters {
    
    fun getSerializer(): KSerializer<ExecuteSlackCompatibleWebhookRequestParameters>
    
}