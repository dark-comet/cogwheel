package xyz.darkcomet.cogwheel.aspects

internal class DiscordClientAspects {
    
    val gateway = Gateway()
    
    class Gateway {
        val connectionAttemptStarted = Aspect<ConnectionAttemptStartedArgs>()
        data class ConnectionAttemptStartedArgs(
            val sessionCount: Int,
            val isResume: Boolean,
            val shouldResumeNextAttempt: Boolean
        )
        
        val fetchGatewayUrlComplete = Aspect<FetchGatewayUrlCompleteArgs>()
        data class FetchGatewayUrlCompleteArgs(val gatewayUrl: String?)
    }
    
}