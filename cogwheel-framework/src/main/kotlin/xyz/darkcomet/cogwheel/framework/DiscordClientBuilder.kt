package xyz.darkcomet.cogwheel.framework

import xyz.darkcomet.cogwheel.core.CwDiscordClientBuilder
import xyz.darkcomet.cogwheel.core.primitives.auth.Token
import xyz.darkcomet.cogwheel.framework.impl.DiscordClientImpl

class DiscordClientBuilder
internal constructor(token: Token) : CwDiscordClientBuilder(token) {
    
    internal fun buildClient(): DiscordClient {
        val cwClient = super.buildInternal()
        return DiscordClientImpl(cwClient)
    }
    
    internal companion object {
        fun from(token: Token): DiscordClientBuilder {
            return DiscordClientBuilder(token)
        }
    }
}