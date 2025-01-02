package xyz.darkcomet.cogwheel.core

import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.impl.DiscordClientImpl
import xyz.darkcomet.cogwheel.core.impl.DiscordClientSettings
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.models.Intents
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient

class DiscordClientBuilder 
internal constructor(token: Token) {
    
    var clientVersion: String?
        get() = settings.customClientVersion
        set(value) { settings.customClientVersion = value }
    
    var clientUrl: String?
        get() = settings.customClientUrl
        set(value) { settings.customClientUrl = value }
    
    internal var cwHttpClientFactory: CwHttpClient.Factory
        get() = settings.cwHttpClientFactory
        set(value) { settings.cwHttpClientFactory = value }
    
    internal val aspects: DiscordClientAspects
        get() = settings.aspects
    
    internal var gatewayFetchUrlMaxAttempts: Int?
        get() = settings.gatewayFetchUrlMaxAttempts
        set(value) { settings.gatewayFetchUrlMaxAttempts = value }
    
    internal var gatewaySessionReconnectMaxAttempts: Int?
        get() = settings.gatewaySessionReconnectMaxAttempts
        set(value) { settings.gatewaySessionReconnectMaxAttempts = value }
    
    internal var testDisableGatewayHeartbeats: Boolean
        get() = settings.testDisableGatewayHeartbeats
        set(value) { settings.testDisableGatewayHeartbeats = value }
    
    private val settings = DiscordClientSettings(token)
    
    fun useGateway(intents: Intents) {
        settings.gatewayEnabled = true
        settings.gatewayIntents = intents
    }
    
    fun isGatewayEnabled() = settings.gatewayEnabled

    internal fun build(): DiscordClient {
        return DiscordClientImpl(settings)
    }
}