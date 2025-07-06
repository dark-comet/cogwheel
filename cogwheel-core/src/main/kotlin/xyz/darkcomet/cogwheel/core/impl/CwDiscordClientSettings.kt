package xyz.darkcomet.cogwheel.core.impl

import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.primitives.auth.Token
import xyz.darkcomet.cogwheel.core.primitives.Intents
import xyz.darkcomet.cogwheel.core.network.gateway.CwGatewayClient
import xyz.darkcomet.cogwheel.core.network.gateway.impl.KtorGatewayClient
import xyz.darkcomet.cogwheel.core.network.http.CwHttpClient
import xyz.darkcomet.cogwheel.core.network.http.impl.KtorHttpClient
import xyz.darkcomet.cogwheel.core.network.http.ratelimit.CwRateLimitStrategy
import xyz.darkcomet.cogwheel.core.network.http.ratelimit.RateLimitStrategy

internal data class CwDiscordClientSettings(
    val token: Token,
    var cwHttpClientFactory: CwHttpClient.Factory = KtorHttpClient.Factory(),
    var cwGatewayClientFactory: CwGatewayClient.Factory = KtorGatewayClient.Factory(),
    var customClientVersion: String? = null,
    var customClientUrl: String? = null,
    var gatewayEnabled: Boolean = false,
    var gatewayIntents: Intents = Intents.none(),
    var gatewayFetchUrlMaxAttempts: Int? = null,
    var gatewaySessionReconnectMaxAttempts: Int? = null,
    var jsonSerializer: Json = Json { isLenient = true; ignoreUnknownKeys = true },
    var rateLimitStrategy: RateLimitStrategy? = CwRateLimitStrategy(maxWaitSeconds = 65),
    val aspects: DiscordClientAspects = DiscordClientAspects(),
    // Test-use settings
    var testDisableGatewayHeartbeats: Boolean = false
)