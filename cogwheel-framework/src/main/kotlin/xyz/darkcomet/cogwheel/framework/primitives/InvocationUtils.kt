@file:Suppress("unused")

package xyz.darkcomet.cogwheel.framework.primitives

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Future
import java.util.function.Consumer

/*
    This file defines infrastructure that facilitates API invocations for both Kotlin and Java 
    callers. It addresses several requirements:
    
    - Succinct, idiomatic and seamless calls from Kotlin through the `invoke()` convention + 
      lambda-with-receiver pattern.
    
    - Provide Java callers with an ergonomic way to work with Kotlin's coroutines by providing 
      `block()` and `async()` calls. These are only intended for Java callers, so use familiar Java
      library types for parameters.
          
    - Provide different flavors of Invocation depending on the number of required parameters. The 
      general pattern is as follows:
          - Endpoint URL parameters are mandatory and in method parameters.
          - Endpoint optional parameters, body params are in TRequestSpec
          - These methods are `open` for extension so endpoints can override them to provide a more
            developer-friendly parameter name.
       
     Invocations come in two flavors: get-style Invocation which takes mandatory parameters only, 
     and post-style RequestInvocation which takes a TRequestSpec for building a request body. The 
     number suffix indicates the number of mandatory parameters in the Invocation.
 */

// 0 mandatory parameters
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

// 1 mandatory parameter
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

abstract class Invocation2<P1, P2, TResponse> {

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

// no mandatory parameter, optional request spec
abstract class RequestInvocationS<TRequestSpec, TResponse> {
    
    protected abstract fun createRequest(): TRequestSpec
    
    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>
    
    suspend operator fun invoke(
        request: TRequestSpec? = null, 
        config: (TRequestSpec.() -> Unit)? = null
    ) : Response<TResponse> {
        
        val req = request ?: createRequest()
        config?.invoke(req)
        
        return invoke(req)
    }

    fun async(config: Consumer<TRequestSpec>? = null) : Future<Response<TResponse>> {
        val req = createRequest()
        config?.accept(req)

        return async(req)
    }
    
    fun async(request: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(request) }
            .asCompletableFuture()
    }
    
    fun block(config: Consumer<TRequestSpec>? = null) : Response<TResponse> {
        
        val req = createRequest()
        config?.accept(req)

        return block(req)
    }
    
    fun block(request: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(request) }
    }
}

// 1 mandatory parameter + 1 optional request spec
abstract class RequestInvocation1S<P1, TRequestSpec, TResponse> {

    protected abstract fun createRequest(p1: P1): TRequestSpec

    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>
    
    open suspend operator fun invoke(
        p1: P1,
        request: TRequestSpec? = null,
        config: (TRequestSpec.() -> Unit)? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1)
        config?.invoke(req)
        
        return invoke(req)
    }

    open fun async(
        p1: P1,
        config: Consumer<TRequestSpec>? = null
    ) : Future<Response<TResponse>> {
        
        val req: TRequestSpec = createRequest(p1)
        config?.accept(req)

        return async(req)
    }
    
    fun async(request: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(request) }
            .asCompletableFuture()
    }

    open fun block(
        p1: P1,
        config: Consumer<TRequestSpec>? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = createRequest(p1)
        config?.accept(req)

        return block(req)
    }
    
    fun block(request: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(request) }
    }
}

// 2 mandatory parameters + optional request spec
abstract class RequestInvocation2S<P1, P2, TRequestSpec, TResponse> {

    protected abstract fun createRequest(p1: P1, p2: P2): TRequestSpec

    abstract suspend operator fun invoke(request: TRequestSpec) : Response<TResponse>

    suspend operator fun invoke(
        p1: P1, 
        p2: P2,
        request: TRequestSpec? = null,
        config: (TRequestSpec.() -> Unit)? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = request ?: createRequest(p1, p2)
        config?.invoke(req)

        return invoke(req)
    }

    open fun async(
        p1: P1, 
        p2: P2, 
        config: Consumer<TRequestSpec>? = null
    ) : Future<Response<TResponse>> {
    
        val req: TRequestSpec = createRequest(p1, p2)
        config?.accept(req)
        
        return async(req)
    }
    
    fun async(request: TRequestSpec) : Future<Response<TResponse>> {
        return CoroutineScope(Dispatchers.IO)
            .async { invoke(request) }
            .asCompletableFuture()
    }

    open fun block(
        p1: P1, 
        p2: P2, 
        config: Consumer<TRequestSpec>? = null
    ) : Response<TResponse> {
        
        val req: TRequestSpec = createRequest(p1, p2)
        config?.accept(req)

        return block(req)
    }
    
    fun block(request: TRequestSpec) : Response<TResponse> {
        return runBlocking { invoke(request) }
    }
}