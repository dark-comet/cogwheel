package xyz.darkcomet.cogwheel.core.network.http.ratelimit

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import xyz.darkcomet.cogwheel.core.network.http.CwHttpMethod

class CwRateLimitStrategyTest {
    
    @Test
    fun testRecordImpl_groupEntriesByRequestMethodAndRouteId() {
        val strategy = CwRateLimitStrategy(maxWaitSeconds = 75)
        
        val routeId1 = "/test"
        val routeId2 = "/test2"
        
        strategy.recordImpl(CwHttpMethod.GET, routeId1, rateLimitData(remaining = 0, resetAfterSeconds = 60.0))
        var delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId1)
        assertTrue(delaySeconds in 59.5..60.0, delaySeconds.toString())

        strategy.recordImpl(CwHttpMethod.GET, routeId2, rateLimitData(remaining = 0, resetAfterSeconds = 75.0))
        delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId2)
        assertTrue(delaySeconds in 74.5..75.0, delaySeconds.toString())
        
        strategy.recordImpl(CwHttpMethod.PUT, routeId1, rateLimitData(remaining = 0, resetAfterSeconds = 45.0))
        delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.PUT, routeId1)
        assertTrue(delaySeconds in 44.5..45.0, delaySeconds.toString())
    }
    
    @Test
    fun testHasRemainingCalls_noDelay() {
        val strategy = CwRateLimitStrategy(maxWaitSeconds = 75)

        val routeId = "/test"

        strategy.recordImpl(CwHttpMethod.GET, routeId, rateLimitData(remaining = 1, resetAfterSeconds = 60.0))
        val delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertEquals(0.0, delaySeconds, delaySeconds.toString())
    }
    
    @Test
    fun testNoBucket_resetTimeWithinThreshold_delayNextRequest() {
        val maxWaitSeconds = 5
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"

        for (i in 0..maxWaitSeconds) {
            strategy.recordImpl(CwHttpMethod.GET, routeId, rateLimitData(remaining = 0, resetAfterSeconds = i.toDouble()))

            assertTrue(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))
            
            val delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
            assertTrue(delaySeconds in (delaySeconds - 0.5) .. delaySeconds, delaySeconds.toString())
        }
    }

    @Test
    fun testHasBucket_resetTimeWithinThreshold_delayNextRequestAcrossSharedRouteId() {
        val maxWaitSeconds = 10
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"
        val routeId2 = "/test2"

        val firstRateLimitData = rateLimitData(remaining = 1, resetAfterSeconds = 3.0, bucket = "testBucket")
        val secondRateLimitData = rateLimitData(remaining = 0, resetAfterSeconds = 7.5, bucket = "testBucket")
        
        strategy.recordImpl(CwHttpMethod.GET, routeId, firstRateLimitData)
        strategy.recordImpl(CwHttpMethod.GET, routeId2, secondRateLimitData)

        assertTrue(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))

        // Indirectly assert that both routes use secondRateLimitData since they share the same bucket
        var delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertTrue(delaySeconds in 7.45 .. 7.5, delaySeconds.toString())
        
        delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId2)
        assertTrue(delaySeconds in 7.45 .. 7.5, delaySeconds.toString())
    }
    
    @Test
    fun testNoBucket_noRemainingCalls_resetTimeOutsideThreshold_abortsRequest() {
        val maxWaitSeconds = 5
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"

        val data = rateLimitData(
            remaining = 0,
            resetAfterSeconds = maxWaitSeconds + 60.0
        )
        
        strategy.recordImpl(CwHttpMethod.GET, routeId, data)

        assertFalse(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))
    }
    
    @Test
    fun testHasBucket_noRemainingCalls_resetTimeOutsideThreshold_abortsRequest() {
        val maxWaitSeconds = 5
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"
        val routeId2 = "/test2"

        val firstData = rateLimitData(
            remaining = 1,
            resetAfterSeconds = maxWaitSeconds + 60.0,
            bucket = "testBucket"
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, firstData)

        val secondData = rateLimitData(
            remaining = 0,
            resetAfterSeconds = maxWaitSeconds + 57.0,
            bucket = "testBucket"
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId2, secondData)
        
        assertFalse(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))
        assertFalse(strategy.isRetryableImpl(CwHttpMethod.GET, routeId2))
    }
    
    @Test
    fun testRouteMappingChanged_differentBucketId_handledCorrectly() {
        testBucketChange("testBucket", "anotherBucket")
    }
    
    @Test
    fun testRouteMappingChanged_nullToBucketId_handledCorrectly() {
        testBucketChange(null, "someBucket")
    }
    
    private fun testBucketChange(oldBucket: String?, newBucket: String?) {
        val maxWaitSeconds = 60
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"
        val routeId2 = "/test2"

        val firstData = rateLimitData(
            remaining = 1,
            resetAfterSeconds = 60.0,
            bucket = oldBucket
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, firstData)

        val secondData = rateLimitData(
            remaining = 0,
            resetAfterSeconds = 57.0,
            bucket = newBucket // Some API backend change occurs and bucket info changes for this route
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, secondData)

        assertTrue(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))

        var delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertTrue(delaySeconds in 56.5 .. 57.0, delaySeconds.toString())

        // Some other request comes along and updates rate limit data for 'anotherBucket'
        val thirdData = rateLimitData(
            remaining = 1,
            resetAfterSeconds = 75.0,
            bucket = newBucket
        )
        strategy.recordImpl(CwHttpMethod.PUT, routeId2, thirdData)

        // routeId should respond to this change too
        assertFalse(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))

        delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertEquals(0.0, delaySeconds, delaySeconds.toString())
    }
    
    @Test
    fun testRouteMappingChanged_bucketIdToNull_handledCorrectly() {
        val maxWaitSeconds = 60
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"
        val routeId2 = "/test2"

        val firstData = rateLimitData(
            remaining = 1,
            resetAfterSeconds = 60.0,
            bucket = "testBucket"
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, firstData)

        val secondData = rateLimitData(
            remaining = 0,
            resetAfterSeconds = 57.0,
            bucket = "newBucket" // Some API backend change occurs and bucket info changes for this route
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, secondData)

        assertTrue(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))

        var delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertTrue(delaySeconds in 56.5 .. 57.0, delaySeconds.toString())

        // Some other request comes along and updates rate limit data for 'anotherBucket'
        val thirdData = rateLimitData(
            remaining = 1,
            resetAfterSeconds = 75.0,
            bucket = null
        )
        strategy.recordImpl(CwHttpMethod.PUT, routeId2, thirdData)

        // routeId does not respond to this change
        assertTrue(strategy.isRetryableImpl(CwHttpMethod.GET, routeId))

        delaySeconds = strategy.computeDelaySeconds(CwHttpMethod.GET, routeId)
        assertTrue(delaySeconds in 56.5 .. 57.0, delaySeconds.toString())
    }
    
    @Test
    fun testRecordedUserLimit_resetTimeWithinThreshold_delayAffectsEveryRequest() {
        testUserOrGlobalRateLimitHandling(CwRateLimitStrategy.SCOPE_USER, waitTimeWithinThreshold = true)
    }
    
    @Test
    fun testRecordedUserLimit_resetTimeOutsideThreshold_abortAffectsEveryRequest() {
        testUserOrGlobalRateLimitHandling(CwRateLimitStrategy.SCOPE_USER, waitTimeWithinThreshold = false)
    }
    
    @Test
    fun testRecordedGlobalLimit_resetTimeWithinThreshold_delayAffectsEveryRequest() {
        testUserOrGlobalRateLimitHandling(CwRateLimitStrategy.SCOPE_GLOBAL, waitTimeWithinThreshold = true)
    }

    @Test
    fun testRecordedGlobalLimit_resetTimeOutsideThreshold_abortAffectsEveryRequest() {
        testUserOrGlobalRateLimitHandling(CwRateLimitStrategy.SCOPE_GLOBAL, waitTimeWithinThreshold = false)
    }

    fun testUserOrGlobalRateLimitHandling(scope: String, waitTimeWithinThreshold: Boolean) {
        val maxWaitSeconds = 60
        val strategy = CwRateLimitStrategy(maxWaitSeconds)

        val routeId = "/test"
        val routeId2 = "/test2"
        val routeId3 = "/test3"

        val route1Data = rateLimitData(limit = 10, remaining = 9, resetAfterSeconds = 30.0, bucket = "test")
        strategy.recordImpl(CwHttpMethod.PUT, routeId, route1Data)

        val route2Data = rateLimitData(limit = 30, remaining = 23, resetAfterSeconds = 16.0, bucket = null)
        strategy.recordImpl(CwHttpMethod.POST, routeId2, route2Data)

        val route3Data = rateLimitData(limit = 5, remaining = 0, resetAfterSeconds = 77.0, bucket = "another")
        strategy.recordImpl(CwHttpMethod.PATCH, routeId3, route3Data)

        // Then we encounter a user/global rate limit
        val globalResetAfterSeconds = if (waitTimeWithinThreshold) 1.0 else maxWaitSeconds + 7.0
        
        val userOrGlobalRateLimitData = rateLimitData(
            remaining = 0,
            resetAfterSeconds = globalResetAfterSeconds,
            scope = scope
        )
        strategy.recordImpl(CwHttpMethod.GET, routeId, userOrGlobalRateLimitData)

        // Every endpoint should be rejected
        var route1DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.PUT, routeId)
        assertTrue(route1DelaySeconds in globalResetAfterSeconds - 0.5 .. globalResetAfterSeconds, route1DelaySeconds.toString())

        var route2DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.POST, routeId)
        assertTrue(route2DelaySeconds in globalResetAfterSeconds - 0.5 .. globalResetAfterSeconds, route2DelaySeconds.toString())

        var route3DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.PATCH, routeId)
        assertTrue(route3DelaySeconds in globalResetAfterSeconds - 0.5 .. globalResetAfterSeconds, route3DelaySeconds.toString())

        if (waitTimeWithinThreshold) {
            // Wait out the user/global limit
            Thread.sleep(1500)

            // Every endpoint should not have delays
            route1DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.PUT, routeId)
            assertEquals(0.0, route1DelaySeconds, route1DelaySeconds.toString())

            route2DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.POST, routeId2)
            assertEquals(0.0, route2DelaySeconds, route2DelaySeconds.toString())

            route3DelaySeconds = strategy.computeDelaySeconds(CwHttpMethod.PATCH, routeId3)
            assertEquals(0.0, route3DelaySeconds, route3DelaySeconds.toString())

            runBlocking {
                assertTrue(strategy.prepareRequestSubmitImpl(CwHttpMethod.PUT, routeId))
                assertTrue(strategy.prepareRequestSubmitImpl(CwHttpMethod.POST, routeId2))
                assertTrue(strategy.prepareRequestSubmitImpl(CwHttpMethod.PATCH, routeId3))
            }
        } else {
            runBlocking {
                assertFalse(strategy.prepareRequestSubmitImpl(CwHttpMethod.PUT, routeId))
                assertFalse(strategy.prepareRequestSubmitImpl(CwHttpMethod.POST, routeId2))
                assertFalse(strategy.prepareRequestSubmitImpl(CwHttpMethod.PATCH, routeId3))
            }
        }
    }
    
    private fun rateLimitData(
        lastUpdateNanoTime: Long = System.nanoTime(),
        limit: Int = 1,
        remaining: Int = 1,
        resetAfterSeconds: Double,
        bucket: String? = null,
        scope: String? = null
    ): CwRateLimitStrategy.RateLimitData {

        return CwRateLimitStrategy.RateLimitData(
            lastUpdateNanoTime,
            limit,
            remaining,
            System.currentTimeMillis() / 1000.0 + resetAfterSeconds,
            resetAfterSeconds,
            bucket,
            scope
        )
    }
}