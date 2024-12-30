package xyz.darkcomet.cogwheel.network.gateway.impl

import xyz.darkcomet.cogwheel.models.ShardId
import xyz.darkcomet.cogwheel.network.CancellationToken
import java.util.concurrent.atomic.AtomicInteger

internal class KtorGatewaySession(
    private val client: KtorGatewayClient, 
    private val sessionCancellation: CancellationToken
) {
    private var initialized = false
    private var heartbeatStarted = false
    
    var apiVersion: Int? = null
    var sessionId: String? = null
    var resumeGatewayUrl: String? = null
    var shard: ShardId? = null
    var heartbeatIntervalMs: Long? = null
    var lastReceivedSequenceNumber = AtomicInteger(0)
    
    private var heartbeatManager: HeartbeatManager? = null
    
    fun initialize(
        apiVersion: Int,
        sessionId: String,
        resumeGatewayUrl: String,
        shard: ShardId?,
        heartbeatIntervalMs: Long
    ) {
        synchronized(this) {
            if (initialized) {
                throw IllegalStateException("Session is already initialized")
            }
            
            this.apiVersion = apiVersion
            this.sessionId = sessionId
            this.resumeGatewayUrl = resumeGatewayUrl
            this.shard = shard
            this.heartbeatIntervalMs = heartbeatIntervalMs

            this.initialized = true
        }
    }
    
    fun beginBackgroundHeartbeats() {
        synchronized(this) {
            if (!initialized) {
                throw IllegalStateException("Session is not initialized!")
            }
            if (heartbeatStarted) {
                throw IllegalStateException("Background heartbeats already started!")
            }

            val heartbeatManager = HeartbeatManager(
                sessionId!!, 
                heartbeatIntervalMs!!, 
                client, 
                lastReceivedSequenceNumber,
                sessionCancellation
            )
            this.heartbeatManager = heartbeatManager
            this.heartbeatStarted = true
        }

        heartbeatManager!!.beginBackgroundHeartbeats()
    }
}