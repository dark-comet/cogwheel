package xyz.darkcomet.cogwheel.core.network.http.ratelimit

import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest

internal class CwRateLimitStrategy(private val maxWaitSeconds: Int) : RateLimitStrategy {

    private var perUserOrGlobalRateLimit: RateLimitData? = null
    private val rateLimitDataByBucket = LinkedHashMap<String, RateLimitData>()
    private val rateLimitDataByRoute = LinkedHashMap<String, RateLimitData>()
    
    private val routeMappingDataByKey = LinkedHashMap<String, RouteMappingInfo>() 

    private val logger = LoggerFactory.getLogger(CwRateLimitStrategy::class.java)
    
    
    override fun record(request: CwHttpRequest, response: HttpResponse) {
        parseRateLimitData(response)?.let { rateLimitData ->
            recordImpl(request.method, request.rateLimitRouteIdentifier, rateLimitData) 
        }
    }

    internal fun recordImpl(method: CwHttpMethod, routeId: String, newData: RateLimitData) {
        val routeKey = getRouteKey(method, routeId)
        val bucket: String? = newData.bucket

        synchronized(this) {
            handleMappingTypePossiblyChanged(routeKey, newData)
            
            val recordedData = getRecordedRateLimitData(routeKey, bucket)

            if (recordedData != null && isNewDataStale(newData, recordedData)) {
                logger.trace("Skipped recording rate limit data for call '{}': response data is stale", routeKey)
                return
            }

            recordDo(routeKey, bucket, newData, recordedData)
        }
    }

    private fun handleMappingTypePossiblyChanged(routeKey: String, newData: RateLimitData) {
        val lastKnownRouteMappingData = routeMappingDataByKey[routeKey]

        if (lastKnownRouteMappingData != null) {
            val lastKnownMappingType = lastKnownRouteMappingData.type

            if (newData.bucket == null && lastKnownMappingType == RouteMappingType.BUCKET) {
                rateLimitDataByBucket.remove(lastKnownRouteMappingData.lastKnownBucketId)
                routeMappingDataByKey.remove(routeKey)
            } else if (newData.bucket != null && lastKnownMappingType == RouteMappingType.PER_ROUTE) {
                rateLimitDataByRoute.remove(lastKnownRouteMappingData.routeKey)
                routeMappingDataByKey.remove(routeKey)
            }
        }
    }

    private fun getRecordedRateLimitData(routeKey: String, canonicalBucketId: String?): RateLimitData? {
        val mappingData = routeMappingDataByKey[routeKey]
        
        return when (mappingData?.type) {
            RouteMappingType.BUCKET -> rateLimitDataByBucket[canonicalBucketId]
            RouteMappingType.PER_ROUTE -> rateLimitDataByRoute[routeKey]
            null -> null
        }
    }
    
    private fun getLocallyCachedRateLimitData(routeKey: String): RateLimitData? {
        if (perUserOrGlobalRateLimit != null) {
            return perUserOrGlobalRateLimit
        }
        
        val mappingData = routeMappingDataByKey[routeKey]
        return getRecordedRateLimitData(routeKey, mappingData?.lastKnownBucketId)
    }

    // Prevent stale rate limit response data overwrite current ones
    //
    // NOTE: A timestamp-based check will never work since it's impossible to know 
    //       whether the time taken is due to routing delays.
    private fun isNewDataStale(newData: RateLimitData, recordedData: RateLimitData): Boolean {
        val currentDataResetsEarlier = recordedData.resetOnUtcSeconds > newData.resetOnUtcSeconds
        val currentDataResetsAtSameTime = recordedData.resetOnUtcSeconds == newData.resetOnUtcSeconds
        val lastRecordedDataHasFewerCallsRemaining = recordedData.remaining < newData.remaining
        
        return currentDataResetsEarlier || (currentDataResetsAtSameTime && lastRecordedDataHasFewerCallsRemaining)
    }

    private fun recordDo(
        routeKey: String,
        canonicalBucketId: String?,
        newData: RateLimitData,
        recordedData: RateLimitData?
    ) {
        if (newData.scope == SCOPE_GLOBAL || newData.scope == SCOPE_USER) {
            perUserOrGlobalRateLimit = newData
        } else {
            if (recordedData == null) {
                if (canonicalBucketId != null) {
                    routeMappingDataByKey[routeKey] = RouteMappingInfo(routeKey, RouteMappingType.BUCKET, canonicalBucketId)
                    rateLimitDataByBucket[canonicalBucketId] = newData
                } else {
                    routeMappingDataByKey[routeKey] = RouteMappingInfo(routeKey, RouteMappingType.PER_ROUTE, lastKnownBucketId = null)
                    rateLimitDataByRoute[routeKey] = newData
                }
            } else {
                recordedData.replaceValues(newData)
            }
        }
        
        if (perUserOrGlobalRateLimit != null 
            && perUserOrGlobalRateLimit!!.remaining <= 0 
            && getSecondsToWait(perUserOrGlobalRateLimit!!) <= 0) {
            
            logger.trace("perUserOrGlobalRateLimit expired")
            perUserOrGlobalRateLimit = null
        }
        
        pruneEntryCounts()
    }

    private fun pruneEntryCounts() {
        // TODO: Can be optimized to remove the last frequently accessed entry (or oldest accessed)
        //       The first item returned by iterator may be the most frequently used.
        
        while (routeMappingDataByKey.size > MAX_MAPPING_DATA_ENTRIES) {
            val removedEntry = routeMappingDataByKey.iterator().next()

            val routeKey = removedEntry.key
            val routeMappingInfo = removedEntry.value
            
            when (routeMappingInfo.type) {
                RouteMappingType.BUCKET -> {
                    rateLimitDataByBucket.remove(routeMappingInfo.lastKnownBucketId!!)
                }
                RouteMappingType.PER_ROUTE -> {
                    rateLimitDataByRoute.remove(routeKey)
                }
            }
        }
    }

    private fun parseRateLimitData(response: HttpResponse): RateLimitData? {
        val limit = response.headers[HEADER_RATE_LIMIT_LIMIT]?.toInt() ?: return null
        val remaining = response.headers[HEADER_RATE_LIMIT_REMAINING]?.toInt() ?: return null
        val resetOnUtc = response.headers[HEADER_RATE_LIMIT_RESET]?.toDouble() ?: return null
        val resetAfterSeconds = response.headers[HEADER_RATE_LIMIT_RESET_AFTER]?.toDouble() ?: return null
        val bucket = response.headers[HEADER_RATE_LIMIT_BUCKET] ?: return null
        val scope = response.headers[HEADER_RATE_LIMIT_SCOPE]
        
        return RateLimitData(System.nanoTime(), limit, remaining, resetOnUtc, resetAfterSeconds, bucket, scope)
    }

    override fun isRetryable(request: CwHttpRequest, response: HttpResponse, submitAttemptCount: Int): Boolean {
        return isRetryableImpl(request.method, request.rateLimitRouteIdentifier)
    }

    internal fun isRetryableImpl(requestMethod: CwHttpMethod, routeIdentifier: String): Boolean {
        val routeKey = getRouteKey(requestMethod, routeIdentifier)
        
        synchronized(this) {
            val recordedData = getLocallyCachedRateLimitData(routeKey)

            if (recordedData != null && getSecondsToWait(recordedData) > maxWaitSeconds) {
                return false
            }
        }
        
        return true
    }

    override suspend fun prepareForRetry(request: CwHttpRequest, response: HttpResponse, currentSubmitAttemptCount: Int) {
    }
    
    internal fun computeDelaySeconds(requestMethod: CwHttpMethod, routeIdentifier: String): Double {
        val routeKey = getRouteKey(requestMethod, routeIdentifier)
        val delaySeconds: Double

        synchronized(this) {
            val recordedData = getLocallyCachedRateLimitData(routeKey)
                ?: throw IllegalStateException("prepareRetry() has not recorded rate limit data for: $routeKey")

            delaySeconds = if (recordedData.remaining == 0) getSecondsToWait(recordedData) else 0.0
        }
        
        return delaySeconds
    }

    override suspend fun prepareRequestSubmit(request: CwHttpRequest): Boolean {
        return prepareRequestSubmitImpl(request.method, request.rateLimitRouteIdentifier, delay = true)
    }

    internal suspend fun prepareRequestSubmitImpl(requestMethod: CwHttpMethod, routeId: String, delay: Boolean = true): Boolean {
        val routeKey = getRouteKey(requestMethod, routeId)

        getLocallyCachedRateLimitData(routeKey)?.let { localData ->
            if (localData.remaining <= 0) {
                val secondsToWait = getSecondsToWait(localData)

                if (secondsToWait > maxWaitSeconds) {
                    return false // Unacceptable wait time, abort request
                }

                if (delay) {
                    delay(secondsToWait.toLong() * 1000L)
                }
            }
        }
        
        return true
    }

    internal fun getSecondsToWait(rateLimitData: RateLimitData): Double {
        return (rateLimitData.resetOnUtcSeconds - (System.currentTimeMillis() / 1000.0)).coerceAtLeast(0.0)
    }

    companion object {

        private const val HEADER_RATE_LIMIT_LIMIT = "X-RateLimit-Limit"
        private const val HEADER_RATE_LIMIT_REMAINING = "X-RateLimit-Remaining"
        private const val HEADER_RATE_LIMIT_RESET = "X-RateLimit-Reset"
        private const val HEADER_RATE_LIMIT_RESET_AFTER = "X-RateLimit-Reset-After"
        private const val HEADER_RATE_LIMIT_BUCKET = "X-RateLimit-Bucket"
        private const val HEADER_RATE_LIMIT_SCOPE = "X-RateLimit-Scope"

        internal const val SCOPE_USER = "user"
        internal const val SCOPE_SHARED = "shared"
        internal const val SCOPE_GLOBAL = "global"
        
        internal const val MAX_MAPPING_DATA_ENTRIES = 10_000
        
        fun getRouteKey(method: CwHttpMethod, routeName: String): String {
            return "${method}:${routeName}"
        }
    }
    
    // all mutable: performance-sensitive code
    internal data class RateLimitData(
        var lastUpdateNanoTime: Long,
        var limit: Int,
        var remaining: Int,
        var resetOnUtcSeconds: Double,
        var resetAfterSeconds: Double,
        var bucket: String?,
        var scope: String?
    ) {
        fun replaceValues(newData: RateLimitData) {
            lastUpdateNanoTime = System.nanoTime()
            limit = newData.limit
            remaining = newData.remaining
            resetOnUtcSeconds = newData.resetOnUtcSeconds
            resetAfterSeconds = newData.resetAfterSeconds
            bucket = newData.bucket
            scope = newData.scope
        }
    }

    private enum class RouteMappingType {
        BUCKET,
        PER_ROUTE
    }
    
    private class RouteMappingInfo(
        val routeKey: String,
        var type: RouteMappingType,
        var lastKnownBucketId: String? = null
    )
}