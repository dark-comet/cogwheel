package xyz.darkcomet.cogwheel.core.network.gateway

import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.core.events.Event
import xyz.darkcomet.cogwheel.core.impl.authentication.Token
import xyz.darkcomet.cogwheel.core.primitives.Intents
import xyz.darkcomet.cogwheel.core.network.CancellationToken
import xyz.darkcomet.cogwheel.core.network.gateway.events.GatewaySendEvent

internal interface CwGatewayClient {
    
    suspend fun start(
        cancellationToken: CancellationToken,
        gatewayUrlFetcher: suspend () -> String?
    )
    
    fun sendEventAsync(event: GatewaySendEvent)
    
    fun onEventReceived(listener: (Event<*>) -> Unit)

    @FunctionalInterface
    interface Factory {
        fun create(
            token: Token,
            intents: Intents,
            libName: String,
            aspects: DiscordClientAspects.Gateway,
            settings: Settings
        ): CwGatewayClient
    }
    
    data class Settings(
        val jsonSerializer: Json,
        val disableHeartbeats: Boolean,
        val fetchUrlMaxAttempts: Int?,
        val reconnectMaxAttempts: Int?,
    )
}