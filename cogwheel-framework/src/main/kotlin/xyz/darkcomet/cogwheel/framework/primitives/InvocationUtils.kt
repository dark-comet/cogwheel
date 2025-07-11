@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.primitives

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Future
import java.util.function.Consumer

abstract class Invocation0<TResponse> {
    
    abstract suspend operator fun invoke() : Response<TResponse>

    open fun async() : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke() }
            .asCompletableFuture()
    }

    open fun block() : Response<TResponse> {
        return runBlocking { invoke() }
    }

}

abstract class Invocation1<P1, TResponse> {

    abstract suspend operator fun invoke(p1: P1) : Response<TResponse>

    open fun async(p1: P1) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1) }
            .asCompletableFuture()
    }

    open fun block(p1: P1) : Response<TResponse> {
        return runBlocking { invoke(p1) }
    }

}

abstract class RequestInvocationS<TRequestSpec, TResponse> {
    
    protected abstract fun createRequest(): TRequestSpec
    
    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>
    
    suspend operator fun invoke(
        request: TRequestSpec? = null, 
        config: Consumer<TRequestSpec>? = null
    ) : Response<TResponse> {
        
        val req = request ?: createRequest()
        config?.accept(req)
        
        return invoke(req)
    }

    @JvmOverloads
    open fun async(
        config: Consumer<TRequestSpec>? = null, 
        request: TRequestSpec? = null
    ) : Future<Response<TResponse>> {
        
        val req: TRequestSpec = request ?: createRequest()
        config?.accept(req)

        return CoroutineScope(Dispatchers.IO)
            .async { invoke(req) }
            .asCompletableFuture()
    }
    
    @JvmOverloads
    open fun block(
        config: Consumer<TRequestSpec>? = null, 
        request: TRequestSpec? = null
    ) : Response<TResponse> {
        
        val req = request ?: createRequest()
        config?.accept(req)

        return runBlocking { invoke(req) }
    }
}

// 1 mandatory parameter
abstract class RequestInvocation1S<P1, TRequestSpec, TResponse> {

    protected abstract fun createRequest(p1: P1): TRequestSpec

    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>
    
    open suspend operator fun invoke(
        p1: P1,
        config: Consumer<TRequestSpec>? = null,
        request: TRequestSpec? = null,
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1)
        config?.accept(req)
        
        return invoke(req)
    }

    @JvmOverloads
    open fun async(
        p1: P1,
        config: Consumer<TRequestSpec>? = null,
        request: TRequestSpec? = null
    ) : Future<Response<TResponse>> {
        
        val req: TRequestSpec = request ?: createRequest(p1)
        config?.accept(req)

        return CoroutineScope(Dispatchers.IO)
            .async { invoke(req) }
            .asCompletableFuture()
    }

    @JvmOverloads
    open fun block(
        p1: P1,
        config: Consumer<TRequestSpec>? = null,
        request: TRequestSpec? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1)
        config?.accept(req)

        return runBlocking { invoke(req) }
    }
}

abstract class RequestInvocation2<P1, P2, TResponse> {

    abstract suspend operator fun invoke(p1: P1, p2: P2) : Response<TResponse>
    
    open fun async(p1: P1, p2: P2) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(p1, p2) }
            .asCompletableFuture()
    }

    open fun block(p1: P1, p2: P2) : Response<TResponse> {
        return runBlocking { invoke(p1, p2) }
    }

}

abstract class RequestInvocation2S<P1, P2, TRequestSpec, TResponse> {

    protected abstract fun createRequest(p1: P1, p2: P2): TRequestSpec

    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>

    suspend operator fun invoke(
        p1: P1, 
        p2: P2,
        request: TRequestSpec? = null,
        config: Consumer<TRequestSpec>? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1, p2)
        config?.accept(req)

        return invoke(req)
    }

    @JvmOverloads
    open fun async(
        p1: P1, 
        p2: P2, 
        config: Consumer<TRequestSpec>? = null, 
        request: TRequestSpec? = null
    ) : Future<Response<TResponse>> {
    
        val req: TRequestSpec = request ?: createRequest(p1, p2)
        config?.accept(req)
        
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(req) }
            .asCompletableFuture()
    }

    @JvmOverloads
    open fun block(
        p1: P1, 
        p2: P2, 
        config: Consumer<TRequestSpec>? = null, 
        request: TRequestSpec? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1, p2)
        config?.accept(req)

        return runBlocking { invoke(req) }
    }
}