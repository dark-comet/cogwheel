package xyz.darkcomet.cogwheel.core.network.http

import io.ktor.client.request.HttpRequestBuilder
import xyz.darkcomet.cogwheel.core.impl.CwDiscordClientSettings
import xyz.darkcomet.cogwheel.core.impl.models.CwConfiguration
import xyz.darkcomet.cogwheel.core.network.objects.FileSupplier

internal interface CwHttpClient {
    
    suspend fun submit(request: CwHttpRequest): CwHttpResponse.Raw
    
    @FunctionalInterface
    interface Factory {
        fun create(settings: CwDiscordClientSettings, config: CwConfiguration): CwHttpClient
    }
}