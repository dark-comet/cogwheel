package xyz.darkcomet.cogwheel.core.network.http

import xyz.darkcomet.cogwheel.core.impl.DiscordClientSettings
import xyz.darkcomet.cogwheel.core.impl.models.CwConfiguration

internal interface CwHttpClient {
    
    suspend fun submit(request: CwHttpRequest): CwHttpResponse.Raw

    @FunctionalInterface
    interface Factory {
        fun create(settings: DiscordClientSettings, config: CwConfiguration): CwHttpClient
    }
}