package xyz.darkcomet.cogwheel.core.network.http

import kotlinx.serialization.DeserializationStrategy

interface CwHttpResponse<T> {
    val raw: Raw
    val data: T?
    
    interface Raw {
        val success: Boolean
        val statusCode: Int
        val statusMessage: String
        val bodyContent: String?

        fun withNoData(): CwHttpResponse<Unit>
        fun <T> withData(strategy: Raw.() -> T?) : CwHttpResponse<T>
        fun <T> withData(strategy: DeserializationStrategy<T>) : CwHttpResponse<T>
    }
}