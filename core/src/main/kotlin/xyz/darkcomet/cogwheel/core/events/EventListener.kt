package xyz.darkcomet.cogwheel.core.events

@FunctionalInterface
fun interface EventListener<T : Event<*>> {
    
    fun eventReceived(event: T)
    
}