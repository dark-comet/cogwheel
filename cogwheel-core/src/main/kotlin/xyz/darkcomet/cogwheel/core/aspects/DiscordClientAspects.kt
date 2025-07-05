package xyz.darkcomet.cogwheel.core.aspects

internal class DiscordClientAspects {
    
    internal val gateway = Gateway()
    
    internal class Gateway {
        internal val connectionAttemptStarted = Aspect<ConnectionAttemptStartedArgs>()
        internal data class ConnectionAttemptStartedArgs(
            val sessionCount: Int,
            val isResume: Boolean,
            val shouldResumeNextAttempt: Boolean
        )
        
        internal val fetchGatewayUrlComplete = Aspect<FetchGatewayUrlCompleteArgs>()
        internal data class FetchGatewayUrlCompleteArgs(val gatewayUrl: String?)
    }
    
}