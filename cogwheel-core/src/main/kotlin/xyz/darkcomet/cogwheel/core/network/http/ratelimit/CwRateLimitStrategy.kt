package xyz.darkcomet.cogwheel.core.network.http.ratelimit

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest

// TODO: Handle /emoji/* endpoints returning inaccurate rate limit data (per API doc) -- need a route name exclusion system?
// TODO: Handle a route switching from having a bucket -> no bucket and vice versa

internal class CwRateLimitStrategy(
    private val maxRetryCount: Int,
    private val maxWaitSeconds: Int
) : RateLimitStrategy {
    
    private val rateLimitDataByBucket = mutableMapOf<String, RateLimitData>()
    private val rateLimitDataByRoute = mutableMapOf<String, RateLimitData>()
    
    private val routeMappingDataByKey: BiMap<String, RouteMappingInfo> = HashBiMap.create()

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
            val recordedData = getRecordedRateLimitData(routeKey, bucket)

            if (recordedData != null && isNewDataStale(newData, recordedData)) {
                logger.trace("Skipped recording rate limit data for call '{}': response data is stale", routeKey)
                return
            }

            // TODO: Enforce size constraints: no more than... 20k entries per map? evict least recently used
            
            recordDo(routeKey, bucket, newData, recordedData)
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
        val mappingData = routeMappingDataByKey[routeKey]

        return when (mappingData?.type) {
            RouteMappingType.BUCKET -> rateLimitDataByBucket[mappingData.lastKnownBucketId]
            RouteMappingType.PER_ROUTE -> rateLimitDataByRoute[routeKey]
            null -> null
        }
    }

    // Prevent stale rate limit response data overwrite current ones
    // Do this by comparing X-RateLimit-Reset timestamp first. If they are identical,
    // accept the copy with the lowest X-RateLimit-Remaining since rate limit quota can
    // only decrease.
    //
    // NOTE: A timestamp-based check will never work since it's impossible to know 
    //       whether the time taken is due to routing delays.
    private fun isNewDataStale(newData: RateLimitData, recordedData: RateLimitData): Boolean {
        val currentDataResetsEarlier = recordedData.resetOnUtc > newData.resetOnUtc
        val currentDataResetsAtSameTime = recordedData.resetOnUtc == newData.resetOnUtc
        val lastRecordedDataHasFewerCallsRemaining = recordedData.remaining < newData.remaining
        
        return currentDataResetsEarlier || (currentDataResetsAtSameTime && lastRecordedDataHasFewerCallsRemaining)
    }

    private fun recordDo(
        routeKey: String,
        canonicalBucketId: String?,
        newData: RateLimitData,
        recordedData: RateLimitData?
    ) {
        if (recordedData == null) {
            if (canonicalBucketId != null) {
                routeMappingDataByKey[routeKey] = RouteMappingInfo(RouteMappingType.BUCKET, canonicalBucketId)
                rateLimitDataByBucket[canonicalBucketId] = newData
            } else {
                routeMappingDataByKey[routeKey] = RouteMappingInfo(RouteMappingType.PER_ROUTE, lastKnownBucketId = null)
                rateLimitDataByRoute[routeKey] = newData
            }
        } else {
            recordedData.replaceValues(newData)
        }
    }

    private fun parseRateLimitData(response: HttpResponse): RateLimitData? {
        val limit = response.headers[HEADER_RATE_LIMIT_LIMIT]?.toInt() ?: return null
        val remaining = response.headers[HEADER_RATE_LIMIT_REMAINING]?.toInt() ?: return null
        val resetOnUtc = response.headers[HEADER_RATE_LIMIT_RESET]?.toLong() ?: return null
        val resetAfterSeconds = response.headers[HEADER_RATE_LIMIT_RESET_AFTER]?.toDouble() ?: return null
        val bucket = response.headers[HEADER_RATE_LIMIT_BUCKET] ?: return null
        
        return RateLimitData(System.nanoTime(), limit, remaining, resetOnUtc, resetAfterSeconds, bucket)
    }

    override fun isRetryable(request: CwHttpRequest, response: HttpResponse, submitAttemptCount: Int): Boolean {
        val rateLimitData: RateLimitData? = parseRateLimitData(response)
        
        if (rateLimitData != null) {
            val routeKey = getRouteKey(request.method, request.rateLimitRouteIdentifier)
            val bucket = rateLimitData.bucket
            
            synchronized(this) {
                val recordedData = getRecordedRateLimitData(routeKey, bucket)
                
                if (recordedData != null && getSecondsToWait(recordedData) > maxWaitSeconds) {
                    return false
                }
            }
        }
        
        return submitAttemptCount >= maxRetryCount
    }

    override suspend fun prepareForRetry(request: CwHttpRequest, response: HttpResponse, currentSubmitAttemptCount: Int) {
        val routeKey = getRouteKey(request.method, request.rateLimitRouteIdentifier)
        
        val rateLimitData = parseRateLimitData(response)
            ?: throw IllegalStateException("prepareRetry() has no rate limit data for: $routeKey")
        
        val bucket = rateLimitData.bucket
        val delaySeconds: Double
        
        synchronized(this) {
            val recordedData = getRecordedRateLimitData(routeKey, bucket)
                ?: throw IllegalStateException("prepareRetry() has not recorded rate limit data for: $routeKey")
            
            delaySeconds = recordedData.resetAfterSeconds
        }
        
        steppedDelay(delaySeconds)
    }

    private suspend fun steppedDelay(seconds: Double) {
        val steps = Math.ceil(seconds).toInt()

        // Wait in 1s steps to respond to coroutine cancellations
        for (step in 1..steps) {
            val delayMillis = if (step < steps) 1000L else ((seconds * 1000) % 1000).toLong()
            delay(delayMillis)
        }
    }

    override suspend fun prepareRequestSubmit(request: CwHttpRequest): Boolean {
        val routeKey = getRouteKey(request.method, request.rateLimitRouteIdentifier)
        
        getLocallyCachedRateLimitData(routeKey)?.let { localData ->
            if (localData.remaining <= 0) {
                val secondsToWait = getSecondsToWait(localData)
                
                if (secondsToWait > maxWaitSeconds) {
                    logger.trace(
                        "Skipped a request submit due to rate limit reached. routeKey: {}, resetsAfterSeconds: {}", 
                        routeKey, 
                        localData.resetAfterSeconds
                    )
                    return false
                }
                
                steppedDelay(secondsToWait)
            }
        }
        
        return false
    }
    
    private fun getSecondsToWait(rateLimitData: RateLimitData): Double {
        // Figure out how much time elapsed since last recorded rate limit time accurately
        // Problem: System.nanoTime() can't be meaningfully converted back to wall clock time (or can it?)
        // Need a way to measure time, that can be translated to seconds, but does not experience clock drift
        
        TODO("Implement me")
    }

    companion object {
        
        const val HEADER_RATE_LIMIT_LIMIT = "X-RateLimit-Limit"
        const val HEADER_RATE_LIMIT_REMAINING = "X-RateLimit-Remaining"
        const val HEADER_RATE_LIMIT_RESET = "X-RateLimit-Reset"
        const val HEADER_RATE_LIMIT_RESET_AFTER = "X-RateLimit-Reset-After"
        const val HEADER_RATE_LIMIT_BUCKET = "X-RateLimit-Bucket"
        
        fun getRouteKey(method: CwHttpMethod, routeName: String): String {
            return "${method}:${routeName}"
        }
    }
    
    // all mutable: performance-sensitive code
    internal data class RateLimitData(
        var lastUpdateNanoTime: Long,
        var limit: Int,
        var remaining: Int,
        var resetOnUtc: Long,
        var resetAfterSeconds: Double,
        var bucket: String?
    ) {
        fun replaceValues(newData: RateLimitData) {
            lastUpdateNanoTime = System.nanoTime()
            limit = newData.limit
            remaining = newData.remaining
            resetOnUtc = newData.resetOnUtc
            resetAfterSeconds = newData.resetAfterSeconds
            bucket = newData.bucket
        }
    }

    private enum class RouteMappingType {
        BUCKET,
        PER_ROUTE
    }
    
    private data class RouteMappingInfo(
        var type: RouteMappingType,
        var lastKnownBucketId: String? = null
    )
}