package xyz.darkcomet.cogwheel.core

import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.impl.CwDiscordClientImpl
import xyz.darkcomet.cogwheel.core.impl.CwDiscordClientSettings
import xyz.darkcomet.cogwheel.core.primitives.auth.Token
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.ratelimit.RateLimitStrategy
import xyz.darkcomet.cogwheel.core.primitives.Intents

open class CwDiscordClientBuilder
protected constructor(token: Token) {
    
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
    
    internal var rateLimitStrategy: RateLimitStrategy?
        get() = settings.rateLimitStrategy
        set(value) { settings.rateLimitStrategy = value }
    
    private val settings = CwDiscordClientSettings(token)
    
    fun useGateway(intents: Intents) {
        settings.gatewayEnabled = true
        settings.gatewayIntents = intents
    }
    
    fun isGatewayEnabled() = settings.gatewayEnabled

    protected fun buildInternal(): CwDiscordClient {
        return CwDiscordClientImpl(settings)
    }
    
    internal fun build(): CwDiscordClient {
        return buildInternal()
    }
    
    internal companion object {
        fun from(token: Token): CwDiscordClientBuilder {
            return CwDiscordClientBuilder(token)
        }
    } 
}