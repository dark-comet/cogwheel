package xyz.darkcomet.cogwheel.core.network.http

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import xyz.darkcomet.cogwheel.core.network.objects.FileSupplier
import java.util.*
import kotlin.collections.HashMap

@ConsistentCopyVisibility
data class CwHttpRequest 
internal constructor(
    val method: CwHttpMethod,
    val route: String,
    val queryParameters: Map<String, String>,
    val headers: Map<String, String>,
    val jsonContent: String?,
    val fileContent: FileSupplier?,
    val rateLimitRouteIdentifier: String 
) {
    internal class Builder(
        private val method: CwHttpMethod, 
        private val urlPath: String,
        private val rateLimitRouteIdentifier: String = urlPath
    ) {
        
        private val headers = HashMap<String, String>()
        private val queryParameters = HashMap<String, String>()
        private var jsonContent: String? = null
        private var fileContent: FileSupplier? = null
        
        fun queryStringParam(key: String, value: String) : Builder
            = apply { this.queryParameters[key] = value }
        
        fun optionalQueryStringParam(key: String, value: Any?) : Builder
            = apply { if (value != null) {  queryStringParam(key, value.toString()) } }
        
        fun optionalAuditLogReason(auditLogReason: String?) : Builder
            = apply { if (auditLogReason != null) { headers["X-Audit-Log-Reason"] = auditLogReason  } }
        
        fun <T> jsonParams(value: T, serializationStrategy: SerializationStrategy<T>) : Builder
            = apply { this.jsonContent = JSON_SERIALIZER.encodeToString(serializationStrategy, value) }
        
        fun files(supplier: FileSupplier?) : Builder 
            = apply { this.fileContent = supplier }
        
        internal fun build() : CwHttpRequest {
            return CwHttpRequest(
                method, 
                urlPath,
                Collections.unmodifiableMap(HashMap(queryParameters)), 
                headers, 
                jsonContent, 
                fileContent,
                rateLimitRouteIdentifier
            )
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
            val builder = Builder(method, urlPath, rateLimitRouteIdentifier)
            init?.invoke(builder)
            
            return builder.build()
        }
    }
    
}