package xyz.darkcomet.cogwheel.core.network.http.ratelimit

import io.ktor.client.statement.*
import xyz.darkcomet.cogwheel.core.network.http.CwHttpRequest

// This could be exposed as public API later when there is a need
// Internal for now to minimize the API surface

// Responsibilities:
//
// - Track rate-limit information as requests are sent
//
// - Inform HttpClient whether a request with 429 error status should be retried & additional retry 
//   logic (i.e. an exponential backoff)
//
// - Determine how to handle requests to endpoints known to have rate limit exceeded. In the default 
//   implementation, EITHER reject request locally if it should not be retried OR delay 
//   (i.e. suspend coroutine) until the rate limits refreshed.

internal interface RateLimitStrategy {
    
    fun record(request: CwHttpRequest, response: HttpResponse)
    
    fun isRetryable(request: CwHttpRequest, response: HttpResponse, submitAttemptCount: Int): Boolean
    
    suspend fun prepareForRetry(request: CwHttpRequest, response: HttpResponse, currentSubmitAttemptCount: Int)
    
    suspend fun prepareRequestSubmit(request: CwHttpRequest): Boolean
    
}