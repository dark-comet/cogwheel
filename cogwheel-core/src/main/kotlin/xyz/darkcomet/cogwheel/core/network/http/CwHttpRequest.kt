package xyz.darkcomet.cogwheel.core.network.http

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.collections.HashMap

data class CwHttpRequest 
internal constructor(
    val method: CwHttpMethod,
    val route: String,
    val queryParameters: Map<String, String>,
    val headers: Map<String, String>,
    val bodyContent: String?,
    val rateLimitRouteIdentifier: String 
) {
    internal class Builder(
        private val method: CwHttpMethod, 
        private val urlPath: String,
        private val rateLimitRouteIdentifier: String = urlPath
    ) {
        
        private val headers = HashMap<String, String>()
        private val queryParameters = HashMap<String, String>()
        private var bodyContent: String? = null
        
        fun queryStringParam(key: String, value: String) : Builder {
            queryParameters[key] = value
            return this
        }
        
        fun optionalQueryStringParam(key: String, value: Any?) {
            if (value != null) {
                queryStringParam(key, value.toString())
            }
        }
        
        fun optionalAuditLogReason(auditLogReason: String?) {
            if (auditLogReason != null) {
                headers["X-Audit-Log-Reason"] = auditLogReason
            }
        }
        
        fun <T> jsonParams(value: T, serializationStrategy: SerializationStrategy<T>) : Builder {
            bodyContent = JSON_SERIALIZER.encodeToString(serializationStrategy, value)
            return this
        }
        
        internal fun build() : CwHttpRequest {
            val queryParamsCopy = Collections.unmodifiableMap(HashMap(queryParameters))
            return CwHttpRequest(method, urlPath, queryParamsCopy, headers, bodyContent, rateLimitRouteIdentifier)
        }
    }
    
    companion object {
        
        private val JSON_SERIALIZER = Json { encodeDefaults = false }
        
        internal fun create(
            method: CwHttpMethod,
            urlPath: String,
            rateLimitRouteIdentifier: String = urlPath,
            init: (Builder.() -> Unit)? = null
        ): CwHttpRequest {
            val builder = Builder(method, urlPath)
            init?.invoke(builder)
            
            return builder.build()
        }
    }
    
}