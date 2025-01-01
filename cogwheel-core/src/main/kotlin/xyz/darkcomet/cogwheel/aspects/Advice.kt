package xyz.darkcomet.cogwheel.aspects

internal fun interface Advice<T> {
    fun apply(args: T)
}