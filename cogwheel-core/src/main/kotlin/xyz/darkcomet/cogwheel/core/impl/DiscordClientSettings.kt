package xyz.darkcomet.cogwheel.core.impl

import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.primitives.Intents
import xyz.darkcomet.cogwheel.core.network.gateway.CwGatewayClient
import xyz.darkcomet.cogwheel.core.network.gateway.impl.KtorGatewayClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.impl.KtorHttpClient

internal data class DiscordClientSettings(
    val token: Token,
    var cwHttpClientFactory: CwHttpClient.Factory = KtorHttpClient.Factory(),
    var cwGatewayClientFactory: CwGatewayClient.Factory = KtorGatewayClient.Factory(),
    var customClientVersion: String? = null,
    var customClientUrl: String? = null,
    var gatewayEnabled: Boolean = false,
    var gatewayIntents: Intents = Intents.none(),
    var gatewayFetchUrlMaxAttempts: Int? = null,
    var gatewaySessionReconnectMaxAttempts: Int? = null,
    val aspects: DiscordClientAspects = DiscordClientAspects(),
    // Test-use settings
    var testDisableGatewayHeartbeats: Boolean = false
)