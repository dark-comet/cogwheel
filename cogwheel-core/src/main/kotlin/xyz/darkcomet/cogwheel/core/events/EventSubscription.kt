package xyz.darkcomet.cogwheel.core.events

@FunctionalInterface
interface EventSubscription<T : Event<*>> {
    
    fun eventReceived(event: T)
    
}