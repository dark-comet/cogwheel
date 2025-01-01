package xyz.darkcomet.cogwheel.network.gateway

import xyz.darkcomet.cogwheel.aspects.DiscordClientAspects
import xyz.darkcomet.cogwheel.events.Event
import xyz.darkcomet.cogwheel.impl.authentication.Token
import xyz.darkcomet.cogwheel.models.Intents
import xyz.darkcomet.cogwheel.network.CancellationToken
import xyz.darkcomet.cogwheel.network.gateway.events.GatewaySendEvent

internal interface CwGatewayClient {
    
    suspend fun start(
        cancellationToken: CancellationToken, 
        gatewayUrlFetcher: suspend () -> String?
    )
    
    fun sendEventAsync(event: GatewaySendEvent)
    
    fun onEventReceived(listener: (Event) -> Unit)

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
        val disableHeartbeats: Boolean,
        val fetchUrlMaxAttempts: Int?,
        val reconnectMaxAttempts: Int?,
    )
}