package xyz.darkcomet.cogwheel.framework.modules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.runBlocking
import xyz.darkcomet.cogwheel.framework.primitives.Response
import java.util.concurrent.Future

interface GetEndpoint<TResponse> {
    
    abstract suspend operator fun invoke() : Response<TResponse>

    fun async() : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke() }
            .asCompletableFuture()
    }

    fun block() : Response<TResponse> {
        return runBlocking { invoke() }
    }

}

interface GetEndpoint1<P1, TResponse> {

    suspend operator fun invoke(p1: P1) : Response<TResponse>

    fun async(p1: P1) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1) }
            .asCompletableFuture()
    }

    fun block(p1: P1) : Response<TResponse> {
        return runBlocking { invoke(p1) }
    }

}

interface RequestEndpoint<TRequestSpec, TResponse> {
    
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

@FunctionalInterface
interface RequestBuilder<TRequestSpec> {
    fun configure(requestSpec: TRequestSpec)
}