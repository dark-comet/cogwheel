package xyz.darkcomet.cogwheel.core.network.http.impl

import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.network.http.CwHttpResponse

internal class KtorHttpResponse<T>(
    override val raw: CwHttpResponse.Raw,
    override val data: T?
) : CwHttpResponse<T> {

    internal class Raw(
        private val httpResponse: HttpResponse, 
        override val bodyContent: String
    ) : CwHttpResponse.Raw {
        
        override val success: Boolean
            get() = httpResponse.status.isSuccess()
        
        override val statusCode: Int
            get() = httpResponse.status.value
        
        override val statusMessage: String
            get() = httpResponse.status.description

        override fun withNoData(): CwHttpResponse<Unit> {
            return KtorHttpResponse(this, data = null)
        }

        override fun <T> withData(strategy: CwHttpResponse.Raw.() -> T?): CwHttpResponse<T> {
            val data = strategy.invoke(this)
            return KtorHttpResponse(this, data)
        }

        override fun <T> withData(strategy: DeserializationStrategy<T>): CwHttpResponse<T> {
            if (bodyContent.isBlank() || ContentType.Application.Json != httpResponse.contentType()) {
                return KtorHttpResponse(this, data = null)
            }

            if (success) {
                val data = JSON_DESERIALIZER.decodeFromString(strategy, bodyContent)
                return KtorHttpResponse(this, data)
            } else {
                return KtorHttpResponse(this, data = null)
            }
        }
        
    }

    companion object {
        private val JSON_DESERIALIZER = Json { ignoreUnknownKeys = true }
    }
}