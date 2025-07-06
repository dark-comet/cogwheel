@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.modules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.runBlocking
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future

interface GetEndpoint<TResponse> {
    
    suspend operator fun invoke() : Response<TResponse>

    fun callAsync() : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke() }
            .asCompletableFuture()
    }

    fun call() : Response<TResponse> {
        return runBlocking { invoke() }
    }

}

interface GetEndpoint1<P1, TResponse> {

    suspend operator fun invoke(p1: P1) : Response<TResponse>

    fun callAsync(p1: P1) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1) }
            .asCompletableFuture()
    }

    fun call(p1: P1) : Response<TResponse> {
        return runBlocking { invoke(p1) }
    }

}

interface RequestEndpointS<TRequestSpec, TResponse> {
    
    fun createRequest(): TRequestSpec
    
    suspend operator fun invoke(requestSpec: TRequestSpec) : Response<TResponse>
    
    suspend operator fun invoke(requestSpecConfig: TRequestSpec.() -> Unit) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.invoke(request)

        return invoke(request)
    }
    
    suspend operator fun invoke(requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)
        
        return invoke(request)
    }

    fun callAsync(requestSpecConfig: RequestBuilder<TRequestSpec>) : Future<Response<TResponse>> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)
        
        return callAsync(request)
    }

    fun callAsync(requestSpec: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(requestSpec) }
            .asCompletableFuture()
    }
    
    fun call(requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)
        
        return call(request)
    }

    fun call(requestSpec: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(requestSpec) }
    }
}

interface RequestEndpoint1S<P1, TRequestSpec, TResponse> {

    fun createRequest(p1: P1): TRequestSpec

    suspend operator fun invoke(p1: P1, requestSpec: TRequestSpec) : Response<TResponse>

    suspend operator fun invoke(p1: P1, requestSpecConfig: TRequestSpec.() -> Unit) : Response<TResponse> {
        val request: TRequestSpec = createRequest(p1)
        requestSpecConfig.invoke(request)

        return invoke(p1, request)
    }

    suspend operator fun invoke(p1: P1, requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest(p1)
        requestSpecConfig.configure(request)

        return invoke(p1, request)
    }

    fun async(p1: P1, requestSpecConfig: RequestBuilder<TRequestSpec>) : Future<Response<TResponse>> {
        val request: TRequestSpec = createRequest(p1)
        requestSpecConfig.configure(request)

        return async(p1, request)
    }

    fun async(p1: P1, requestSpec: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1, requestSpec) }
            .asCompletableFuture()
    }

    fun block(p1: P1, requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest(p1)
        requestSpecConfig.configure(request)

        return block(p1, request)
    }

    fun block(p1: P1, requestSpec: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(p1, requestSpec) }
    }
}

interface RequestEndpoint2<P1, P2, TResponse> {

    suspend operator fun invoke(p1: P1, p2: P2) : Response<TResponse>

    fun callAsync(p1: P1, p2: P2) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1, p2) }
            .asCompletableFuture()
    }

    fun call(p1: P1, p2: P2) : Response<TResponse> {
        return runBlocking { invoke(p1, p2) }
    }

}

interface RequestEndpoint2S<P1, P2, TRequestSpec, TResponse> {

    fun createRequest(): TRequestSpec

    suspend operator fun invoke(p1: P1, p2: P2, requestSpec: TRequestSpec) : Response<TResponse>

    suspend operator fun invoke(p1: P1, p2: P2, requestSpecConfig: TRequestSpec.() -> Unit) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.invoke(request)

        return invoke(p1, p2, request)
    }

    suspend operator fun invoke(p1: P1, p2: P2, requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)

        return invoke(p1, p2, request)
    }

    fun callAsync(p1: P1, p2: P2, requestSpecConfig: RequestBuilder<TRequestSpec>) : Future<Response<TResponse>> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)

        return callAsync(p1, p2, request)
    }

    fun callAsync(p1: P1, p2: P2, requestSpec: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1, p2, requestSpec) }
            .asCompletableFuture()
    }

    fun call(p1: P1, p2: P2, requestSpecConfig: RequestBuilder<TRequestSpec>) : Response<TResponse> {
        val request: TRequestSpec = createRequest()
        requestSpecConfig.configure(request)

        return call(p1, p2, request)
    }

    fun call(p1: P1, p2: P2, requestSpec: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(p1, p2, requestSpec) }
    }
}


@FunctionalInterface
interface RequestBuilder<TRequestSpec> {
    fun configure(requestSpec: TRequestSpec)
}