package xyz.darkcomet.cogwheel.core.network

interface CancellationToken {
    fun isCanceled(): Boolean
}