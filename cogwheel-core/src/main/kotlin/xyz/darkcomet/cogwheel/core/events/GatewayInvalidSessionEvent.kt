package xyz.darkcomet.cogwheel.core.events

class GatewayInvalidSessionEvent(val isResumeRecommended: Boolean) : Event<Boolean> {
    
    override val data: Boolean
        get() = isResumeRecommended
    
}