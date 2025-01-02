package xyz.darkcomet.cogwheel.core.aspects

internal class Aspect<T> {
    
    private val advices: MutableList<Advice<T>> = ArrayList()
    
    fun addAdvice(advice: Advice<T>) = advices.add(advice)
    fun removeAdvice(advice: Advice<T>) = advices.remove(advice)
    
    fun applyAdvices(args: T) {
        for (advice in advices) {
            advice.apply(args)
        }
    }
    
}