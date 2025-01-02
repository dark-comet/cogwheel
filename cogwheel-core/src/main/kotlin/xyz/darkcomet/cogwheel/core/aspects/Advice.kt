package xyz.darkcomet.cogwheel.core.aspects

internal fun interface Advice<T> {
    fun apply(args: T)
}